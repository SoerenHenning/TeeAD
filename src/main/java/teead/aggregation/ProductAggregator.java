package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class ProductAggregator implements Aggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.product(timeSeries.toValuesArray());
	}

}
