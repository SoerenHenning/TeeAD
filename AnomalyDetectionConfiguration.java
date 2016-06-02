package anomalydetection;

import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage();
		final SimpleAlertStage alerter = new SimpleAlertStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), anomalyDetector.getInputPort());
		super.connectPorts(anomalyDetector.getNewOutputPort(0.9), alerter.getInputPort());

	}

}
