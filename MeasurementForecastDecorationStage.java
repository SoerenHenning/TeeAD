package anomalydetection;

import kieker.analysis.util.AbstractBiCombinerStage;

import anomalydetection.measurement.ForecastedMeassurement;
import anomalydetection.measurement.Measurement;
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
