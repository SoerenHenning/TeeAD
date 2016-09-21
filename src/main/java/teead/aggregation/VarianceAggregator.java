package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class VarianceAggregator implements Aggregator {

	public VarianceAggregator() {}

	public VarianceAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.variance(timeSeries.toValuesArray());
	}

}
