package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.TimeSeries;

public class MeanAggregator implements IAggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.mean(timeSeries.toArray());
	}

}
