package anomalydetection;

import anomalydetection.forecast.IForecaster;
import teetime.stage.basic.AbstractTransformation;

public class ForecastStage extends AbstractTransformation<TimeSeries, Double> {

	private final IForecaster forecaster;

	public ForecastStage(final IForecaster forecaster) {
		super();
		this.forecaster = forecaster;
	}

	@Override
	protected void execute(final TimeSeries timeSeries) {
		final double forecast = forecaster.forecast(timeSeries);
		this.outputPort.send(forecast);
	}

}
