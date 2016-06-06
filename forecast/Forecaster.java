package anomalydetection.forecast;

import anomalydetection.timeseries.TimeSeries;

public interface Forecaster {

	public double forecast(TimeSeries timeSeries);

}
