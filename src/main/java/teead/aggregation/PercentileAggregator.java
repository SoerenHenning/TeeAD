package teead.aggregation;

import org.apache.commons.math3.stat.StatUtils;

import teead.timeseries.TimeSeries;

public class PercentileAggregator implements Aggregator {

	public final static String PERCENTILE_KEY = "percentile";

	private double percentile;

	public PercentileAggregator(final double percentile) {
		this.setPercentile(percentile);
	}

	public PercentileAggregator(final AggregatorConfiguration configuration) {
		this(Double.valueOf(configuration.get(PERCENTILE_KEY)));
	}

	@Override
	public double aggregate(final TimeSeries timeSeries) {
		return StatUtils.percentile(timeSeries.toValuesArray(), this.getPercentile());
	}

	public double getPercentile() {
		return percentile;
	}

	public void setPercentile(final double percentile) {
		this.percentile = percentile;
	}

}
