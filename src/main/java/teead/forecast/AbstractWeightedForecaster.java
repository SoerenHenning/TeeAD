package teead.forecast;

import teead.timeseries.EquidistantTimeSeries;
import teead.timeseries.TimeSeriesPoint;

/**
 * @author Sören Henning, Christian Claus Wiechmann
 *
 */
public abstract class AbstractWeightedForecaster implements Forecaster {

	public AbstractWeightedForecaster() {}

	public AbstractWeightedForecaster(final ForecasterConfiguration configuration) {
		this();
	}

	@Override
	public double forecast(final EquidistantTimeSeries timeSeries) {
		double weightedSum = 0;
		double totalWeights = 0;

		// more recent entry means more weight
		int position = 1; // We just want to have values > 0
		int size = timeSeries.size();
		for (TimeSeriesPoint point : timeSeries) {
			final double weight = getWeight(position, size);
			totalWeights += weight;
			weightedSum += point.getValue() * weight;
			position++;
		}

		return weightedSum / totalWeights;
	}

	protected abstract double getWeight(final int position, final int size);

}
