package anomalydetection.forecast;

import anomalydetection.TimeSeries;

public interface IForecaster {

	public double forecast(TimeSeries timeSeries);

}
