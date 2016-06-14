package anomalydetection;

import java.io.File;
import java.time.Duration;

import anomalydetection.aggregation.MeanAggregator;
import anomalydetection.forecast.ARIMAForecaster;
import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		// final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 500 * Math.sin(x / 240) + 2000, 250, 0.01, 1000, Duration.ofSeconds(1),
		// 3600);
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 500 * Math.sin(x / 60) + 2000, 500, 0.01, 1000, Duration.ofSeconds(1),
				100);
		// final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 0.5 * x + 1000, 250, 0.01, 1000, Duration.ofSeconds(1),
		// 900);
		final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage(Duration.ofHours(1), Duration.ofSeconds(30), new MeanAggregator(),
				new ARIMAForecaster("192.168.99.100", 6311));
		final SimpleAlertStage alerter = new SimpleAlertStage();
		final PrinterStage printer = new PrinterStage(); // TODO Temp
		final JSONExporter jsonExporter = new JSONExporter(new File("values.json")); // TODO Temp

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), anomalyDetector.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(0.9), alerter.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(), printer.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(), jsonExporter.getInputPort());

	}

}
