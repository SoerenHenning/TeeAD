package anomalydetection.forecast;

import anomalydetection.TimeSeries;

public class SimpleForecaster implements IForecaster {

	@Override
	public double forecast(final TimeSeries timeSeries) {
		return timeSeries.getTimeSeriesPoints().get(0).getValue();
	}

}
