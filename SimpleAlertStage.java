package anomalydetection;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

public class SimpleAlertStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		System.out.println("ALERT! Score: " + measurement.getAnomalyScore());
	}

}
