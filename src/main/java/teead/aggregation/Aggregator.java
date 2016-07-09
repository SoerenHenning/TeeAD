package teead.aggregation;

import teead.timeseries.TimeSeries;

public interface Aggregator {

	public double aggregate(final TimeSeries timeSeries);

}
