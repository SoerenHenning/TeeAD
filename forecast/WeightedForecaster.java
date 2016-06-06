package anomalydetection.forecast;

import anomalydetection.TimeSeries;
import anomalydetection.TimeSeriesPoint;

/**
 * @author Christian Claus Wiechmann, Sören Henning
 *
 */
public class WeightedForecaster implements Forecaster {

	public enum WeightMethod {
		LOGARITHMIC, LINEAR, EXPONENTIAL
	}

	private final WeightMethod weightMethod;

	public WeightedForecaster(final WeightMethod weightMethod) {
		this.weightMethod = weightMethod;
	}

	@Override
	public double forecast(final TimeSeries timeSeries) {
		double weightedSum = 0;
		double totalWeights = 0;

		// more recent entry means more weight
		int position = 1; // Position > 0, because logarithmic forecast method
		for (TimeSeriesPoint point : timeSeries.getTimeSeriesPoints()) {
			final double weight = getWeight(position);
			totalWeights += weight;
			weightedSum += point.getValue() * weight;
			position++;
		}

		return weightedSum / totalWeights;
	}

	private double getWeight(final int position) {
		switch (this.weightMethod) {
		case LOGARITHMIC:
			return Math.log(position);
		case LINEAR:
			return position;
		case EXPONENTIAL:
			return Math.exp(position);
		default:
			return position;
		}
	}

}
