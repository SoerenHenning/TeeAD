package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class GeometricMeanAggregator implements Aggregator {

	public GeometricMeanAggregator() {}

	public GeometricMeanAggregator(final AggregatorConfiguration configuration) {
		this();
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.geometricMean(timeSeries.toValuesArray());
	}

}
