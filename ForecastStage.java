package anomalydetection;

import anomalydetection.forecast.Forecaster;
import anomalydetection.timeseries.EquidistantTimeSeries;
import teetime.stage.basic.AbstractTransformation;

public class ForecastStage extends AbstractTransformation<EquidistantTimeSeries, Double> {

	private final Forecaster forecaster;

	public ForecastStage(final Forecaster forecaster) {
		super();
		this.forecaster = forecaster;
	}

	@Override
	protected void execute(final EquidistantTimeSeries timeSeries) {
		final double forecast = forecaster.forecast(timeSeries);
		this.outputPort.send(forecast);
	}

}
