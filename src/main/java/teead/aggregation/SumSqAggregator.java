package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class SumSqAggregator implements Aggregator {

	public SumSqAggregator() {}

	public SumSqAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.sumSq(timeSeries.toValuesArray());
	}

}
