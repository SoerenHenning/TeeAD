package anomalydetection;

import java.time.Duration;

import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage(x -> 500 * Math.sin(x / 60) + 2000, 250, 0.01, 1000, Duration.ofSeconds(1),
				3600);
		final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage();
		final SimpleAlertStage alerter = new SimpleAlertStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), anomalyDetector.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(0.9), alerter.getInputPort());

	}

}
