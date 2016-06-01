package anomalydetection;

import anomalydetection.measurement.ForecastedMeassurement;
import teetime.framework.AbstractConsumerStage;

public class PrinterStage extends AbstractConsumerStage<ForecastedMeassurement> {

	@Override
	protected void execute(final ForecastedMeassurement measurement) {
		System.out.println(measurement.getTime() + "::" + measurement.getValue() + "::" + measurement.getPrediction());
	}

}
