package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class MinAggregator implements Aggregator {

	public MinAggregator() {}

	public MinAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.min(timeSeries.toValuesArray());
	}

}
