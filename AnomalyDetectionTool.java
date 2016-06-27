package anomalydetection;

import teetime.framework.Execution;

public class AnomalyDetectionTool {

	public static void main(final String[] args) {

		// String ipAddress = "192.168.99.100";
		// int port = 32770;
		// String keyspace = "demo3";
		//
		// Cluster cluster = Cluster.builder().addContactPoint(ipAddress).withPort(port).build();
		// Session session = cluster.connect(keyspace);
		//
		// CassandraDriver cassandraDriver = new CassandraDriver(session, "measurements");
		// cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1200, 1100, 0.11));
		// cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1150, 1000, 0.12));
		// cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1150, 1200, 0.13));
		// cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1200, 1100, 0.12));
		//
		// TimeSeries timeSeries = cassandraDriver.retrieveTimeSeries("abc", Instant.now().minusSeconds(60 * 60), Instant.now());
		// System.out.println(timeSeries);
		//
		// cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1300, 1000, 0.14));
		// cassandraDriver.storeMeasurement("abc", new AnomalyScoredMeasurement(Instant.now(), 1350, 1050, 0.13));
		//
		// session.close();

		final AnomalyDetectionConfiguration configuration = new AnomalyDetectionConfiguration();
		final Execution<AnomalyDetectionConfiguration> analysis = new Execution<AnomalyDetectionConfiguration>(configuration);
		analysis.executeBlocking();
	}

}
