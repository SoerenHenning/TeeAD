package teead.forecast;

import teead.timeseries.EquidistantTimeSeries;
import teead.timeseries.TimeSeriesPoint;

/**
 * @author Christian Claus Wiechmann, Sören Henning
 *
 * @deprecated use {@link AbstractWeightedForecaster} and its subclasses instead
 */
@Deprecated
public class WeightedForecaster implements Forecaster {

	public final static String WEIGHT_METHOD_CONFIGURATION_KEY = "weightMethod";

	public enum WeightMethod {
		LOGARITHMIC, LINEAR, EXPONENTIAL
	}

	private final WeightMethod weightMethod;

	public WeightedForecaster(final WeightMethod weightMethod) {
		this.weightMethod = weightMethod;
	}

	public WeightedForecaster(final ForecasterConfiguration configuration) {
		this(WeightMethod.valueOf(configuration.get(WEIGHT_METHOD_CONFIGURATION_KEY)));
	}

	@Override
	public double forecast(final EquidistantTimeSeries timeSeries) {
		double weightedSum = 0;
		double totalWeights = 0;

		// more recent entry means more weight
		int position = 1; // Position > 0, because logarithmic forecast method
		int size = timeSeries.size();
		for (TimeSeriesPoint point : timeSeries) {
			final double weight = getWeight(position, size);
			totalWeights += weight;
			weightedSum += point.getValue() * weight;
			position++;
		}

		return weightedSum / totalWeights;
	}

	private double getWeight(final int position, final int size) {
		switch (this.weightMethod) {
		case LOGARITHMIC:
			return Math.log(position);
		case LINEAR:
			return position;
		case EXPONENTIAL:
			return Math.exp(position - size);
		default:
			return position;
		}
	}

}
