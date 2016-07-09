package teead.forecast;

import teead.timeseries.EquidistantTimeSeries;

public interface Forecaster {

	public double forecast(final EquidistantTimeSeries timeSeries);

}
