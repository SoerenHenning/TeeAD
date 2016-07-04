package anomalydetection.aggregation;

import anomalydetection.timeseries.TimeSeries;

public interface Aggregator {

	public double aggregate(final TimeSeries timeSeries);

}
