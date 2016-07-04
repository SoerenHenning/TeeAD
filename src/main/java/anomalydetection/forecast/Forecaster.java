package anomalydetection.forecast;

import anomalydetection.timeseries.EquidistantTimeSeries;

public interface Forecaster {

	public double forecast(final EquidistantTimeSeries timeSeries);

}
