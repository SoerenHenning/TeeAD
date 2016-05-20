package anomalydetection;

import anomalydetection.measurement.ForecastedMeassurement;
import anomalydetection.measurement.Measurement;
import teetime.stage.basic.AbstractTransformation;

//TODO temp stage for creating forecasted measurements
public class SimpleForecastDecorationStage extends AbstractTransformation<Measurement, ForecastedMeassurement> {

	@Override
	protected void execute(final Measurement measurement) {

		ForecastedMeassurement forecastedMeassurement = new ForecastedMeassurement(measurement, measurement.getValue() / 2);

		this.outputPort.send(forecastedMeassurement);
	}

}
