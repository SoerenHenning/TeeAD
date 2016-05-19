package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.TimeSeries;

public class SumAggregator implements IAggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.sum(timeSeries.toArray());
	}

}
