package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class ProductAggregator implements Aggregator {

	public ProductAggregator() {}

	public ProductAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.product(timeSeries.toValuesArray());
	}

}
