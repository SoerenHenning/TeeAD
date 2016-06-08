package anomalydetection.aggregation;

import anomalydetection.timeseries.NewTimeSeries;

public interface Aggregator {

	public double aggregate(final NewTimeSeries timeSeries);

}
