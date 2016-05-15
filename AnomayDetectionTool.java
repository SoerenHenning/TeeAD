package anomalydetection;

import teetime.framework.Execution;

public class AnomayDetectionTool {

	public static void main(final String[] args) {

		final AnomalyDetectionConfiguration configuration = new AnomalyDetectionConfiguration();
		final Execution<AnomalyDetectionConfiguration> analysis = new Execution<AnomalyDetectionConfiguration>(configuration);
		analysis.executeBlocking();
	}

}
