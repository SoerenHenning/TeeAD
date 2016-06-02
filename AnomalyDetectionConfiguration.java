package anomalydetection;

import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), anomalyDetector.getInputPort());

	}

}
