package anomalydetection;

import teetime.framework.AbstractConsumerStage;

public class PrinterStage extends AbstractConsumerStage<Measurement> {

	@Override
	protected void execute(final Measurement measurement) {
		System.out.println(measurement.getTime() + "::" + measurement.getForecastedValue());
	}

}
