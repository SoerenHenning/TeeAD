package anomalydetection;

import anomalydetection.forecast.Forecaster;
import anomalydetection.timeseries.TimeSeries;
import teetime.stage.basic.AbstractTransformation;

public class ForecastStage extends AbstractTransformation<TimeSeries, Double> {

	private final Forecaster forecaster;

	public ForecastStage(final Forecaster forecaster) {
		super();
		this.forecaster = forecaster;
	}

	@Override
	protected void execute(final TimeSeries timeSeries) {
		final double forecast = forecaster.forecast(timeSeries);
		this.outputPort.send(forecast);
	}

}
