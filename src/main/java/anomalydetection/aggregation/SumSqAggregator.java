package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.timeseries.TimeSeries;

public class SumSqAggregator implements Aggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.sumSq(timeSeries.toValuesArray());
	}

}