package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.TimeSeries;

public class SumSqAggregator implements IAggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.sumSq(timeSeries.toArray());
	}

}
