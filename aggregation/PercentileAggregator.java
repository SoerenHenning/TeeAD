package anomalydetection.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.TimeSeries;

public class PercentileAggregator implements IAggregator {

	private double percentile;

	public PercentileAggregator(final double percentile) {
		this.setPercentile(percentile);
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.percentile(timeSeries.toArray(), this.getPercentile());
	}

	public double getPercentile() {
		return percentile;
	}

	public void setPercentile(final double percentile) {
		this.percentile = percentile;
	}

}
