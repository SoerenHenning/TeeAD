package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class SumAggregator implements Aggregator {

	public SumAggregator() {}

	public SumAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.sum(timeSeries.toValuesArray());
	}

}
