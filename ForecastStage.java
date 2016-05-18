package anomalydetection;

import anomalydetection.forecast.IForecaster;
import teetime.stage.basic.AbstractTransformation;

public class ForecastStage extends AbstractTransformation<Measurement, Measurement> {

	private final IForecaster forecaster;

	public ForecastStage(final IForecaster forecaster) {
		this.forecaster = forecaster;
	}

	@Override
	protected void execute(final Measurement measurement) {

		double forecast = forecaster.forecast(measurement.getHistory());
		measurement.setForecastedValue(forecast);

		this.outputPort.send(measurement);
	}

}
