package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class MaxAggregator implements Aggregator {

	public MaxAggregator() {}

	public MaxAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.max(timeSeries.toValuesArray());
	}

}
