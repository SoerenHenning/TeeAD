package anomalydetection.forecast;

import anomalydetection.TimeSeries;

public interface Forecaster {

	public double forecast(TimeSeries timeSeries);

}
