package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.timeseries.NewTimeSeries;

public class PercentileAggregator implements Aggregator {

	private double percentile;

	public PercentileAggregator(final double percentile) {
		this.setPercentile(percentile);
	}

	@Override
	public double aggregate(final NewTimeSeries timeSeries) {
		return StatUtils.percentile(timeSeries.toValuesArray(), this.getPercentile());
	}

	public double getPercentile() {
		return percentile;
	}

	public void setPercentile(final double percentile) {
		this.percentile = percentile;
	}

}
