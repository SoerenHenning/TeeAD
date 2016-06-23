package anomalydetection;

import java.time.Instant;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.storage.CassandraDriver;
import anomalydetection.timeseries.TimeSeries;
import teetime.framework.Execution;

public class AnomalyDetectionTool {

	public static void main(final String[] args) {

		String ipAddress = "192.168.99.100";
		int port = 32770;
		String keyspace = "demo2";

		Cluster cluster = Cluster.builder().addContactPoint(ipAddress).withPort(port).build();
		Session session = cluster.connect(keyspace);

		CassandraDriver cassandraDriver = new CassandraDriver(session, "measurements");
		cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1200, 1100, 0.11));

		TimeSeries timeSeries = cassandraDriver.retrieveTimeSeries("abc", Instant.now().minusSeconds(60 * 60), Instant.now());
		System.out.println(timeSeries);

		session.close();

		final AnomalyDetectionConfiguration configuration = new AnomalyDetectionConfiguration();
		final Execution<AnomalyDetectionConfiguration> analysis = new Execution<AnomalyDetectionConfiguration>(configuration);
		analysis.executeBlocking();
	}

}
