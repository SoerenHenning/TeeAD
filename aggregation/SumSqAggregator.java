package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.timeseries.NewTimeSeries;

public class SumSqAggregator implements Aggregator {

	@Override
	public double aggregate(final NewTimeSeries timeSeries) {
		return StatUtils.sumSq(timeSeries.toValuesArray());
	}

}
