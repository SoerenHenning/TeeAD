package teead.anomalyscore;

/**
 * {@link AnomalyScoreCalculator} that calculates the anomaly score using the
 * following formula:
 *
 * s = (p - m) / (p + m)
 * Where m is the measurement, p is the prediction and s is the anomly score.
 *
 * This leads to an anomaly score that is always in the interval [0,1].
 *
 * This is the default anomaly score calculator in OPAD.
 *
 * @author Sören Henning
 *
 */
public class SimpleAnomalyScoreCalculator implements AnomalyScoreCalculator {

	@Override
	public double calculate(final double measurement, final double prediction) {

		if (Double.isNaN(prediction) && Double.isNaN(measurement)) {
			return 0.0d;
		} else if (Double.isNaN(prediction) || Double.isNaN(measurement)) {
			return 1.0d;
		} else {
			return Math.abs((prediction - measurement) / (prediction + measurement));
		}

	}

}
