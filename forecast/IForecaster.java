package anomalydetection.forecast;

import anomalydetection.TimeSeries;

public interface IForecaster<T> {

	public T forecast(TimeSeries<T> timeSeries);

}
