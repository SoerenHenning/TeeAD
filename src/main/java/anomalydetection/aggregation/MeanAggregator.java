package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.timeseries.TimeSeries;

public class MeanAggregator implements Aggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.mean(timeSeries.toValuesArray());
	}

}
