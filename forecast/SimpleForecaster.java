package anomalydetection.forecast;

import anomalydetection.TimeSeries;

public class SimpleForecaster<T> implements IForecaster<T> {

	@Override
	public T forecast(final TimeSeries<T> timeSeries) {
		return timeSeries.getTimeSeriesPoints().get(0).getValue();
	}

}
