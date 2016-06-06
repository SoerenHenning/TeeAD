package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

public class GeometricMeanAggregator implements Aggregator {

	@Override
	public double aggregate(final anomalydetection.timeseries.TimeSeries timeSeries) {
		return StatUtils.geometricMean(timeSeries.toArray());
	}

}
