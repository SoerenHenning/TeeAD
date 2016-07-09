package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class MeanAggregator implements Aggregator {

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.mean(timeSeries.toValuesArray());
	}

}
