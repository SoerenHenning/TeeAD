package teead;

import teead.measurement.ForecastedMeassurement;
import teead.measurement.Measurement;
import teead.util.AbstractBiCombinerStage;
import teetime.framework.OutputPort;

public class MeasurementForecastDecorationStage extends AbstractBiCombinerStage<Double, Measurement> {

	private final OutputPort<ForecastedMeassurement> outputPort = createOutputPort();

	@Override
	protected void combine(final Double prediction, final Measurement measurement) {
		final ForecastedMeassurement forecastedMeassurement = new ForecastedMeassurement(measurement, prediction);
		this.outputPort.send(forecastedMeassurement);
	}

	public final OutputPort<ForecastedMeassurement> getOutputPort() {
		return outputPort;
	}

}
