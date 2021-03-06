package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class SumLogAggregator implements Aggregator {

	public SumLogAggregator() {}

	public SumLogAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.sumLog(timeSeries.toValuesArray());
	}

}
