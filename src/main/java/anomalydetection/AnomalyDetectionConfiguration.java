package anomalydetection;

import java.io.File;
import java.time.Duration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import anomalydetection.aggregation.MeanAggregator;
import anomalydetection.forecast.RegressionForecaster;
import anomalydetection.storage.CassandraDriver;
import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		String ipAddress = "192.168.99.100";
		int port = 32770;
		String keyspace = "demo3";

		Cluster cluster = Cluster.builder().addContactPoint(ipAddress).withPort(port).build();
		Session session = cluster.connect(keyspace);

		final MeasurementGenerator gen = new MeasurementGenerator();
		gen.setFunction(x -> 500 * Math.sin(x / 60) + 2000);
		gen.setNoise(250);
		gen.setAnomalyProbability(0.01);
		gen.setAnomayStrength(1000);
		gen.setMinStepDistance(Duration.ofMillis(500));
		gen.setMinStepDistance(Duration.ofSeconds(2));

		// Create the stages
		final RealTimeMeasurementGeneratorStage generator = new RealTimeMeasurementGeneratorStage(gen, 3600);
		// final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 500 * Math.sin(x / 60) + 2000, 250, 0.01, 1000,
		// Duration.ofSeconds(1),3600);
		// final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 500 * Math.pow(Math.sin(x / 60), 1 / 101) + 2000, 250, 0.01, 1000,
		// Duration.ofSeconds(5), 3600);
		// final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 0.5 * x + 1000, 250, 0.01, 1000, Duration.ofSeconds(1),
		// 900);
		// final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage(Duration.ofHours(1), Duration.ofSeconds(30), new MeanAggregator(), new
		// ARIMAForecaster("192.168.99.100", 6311));
		// final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage(Duration.ofHours(1), Duration.ofSeconds(5), new MeanAggregator(),new
		// RegressionForecaster());
		final AnomalyDetectionStage anomalyDetector = new StorableAnomalyDetectionStage(Duration.ofHours(1), Duration.ofSeconds(5), new MeanAggregator(),
				new RegressionForecaster(), new CassandraDriver(session, "measurements", "temp"));
		final AlertPrinterStage alerter = new AlertPrinterStage();
		final PrinterStage printer = new PrinterStage(); // TODO Temp
		final JSONExportStage jsonExporter = new JSONExportStage(new File("values.json")); // TODO Temp

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), anomalyDetector.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(0.9), alerter.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(), printer.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(), jsonExporter.getInputPort());

		// cluster.close();

	}

}
