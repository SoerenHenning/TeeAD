package teead;

import teead.forecast.Forecaster;
import teead.timeseries.EquidistantTimeSeries;
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
