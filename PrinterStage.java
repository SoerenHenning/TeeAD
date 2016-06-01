package anomalydetection;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

public class PrinterStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		System.out.println(measurement.getTime() + "::" + measurement.getValue()
				+ "::" + measurement.getPrediction() + "::" + measurement.getAnomalyScore());
	}

}
