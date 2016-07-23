package teead.anomalyscore;

/**
 * {@link AnomalyScoreCalculator} that calculates a anomaly score based on the
 * percentage deviation from measured value to predicted value.
 *
 * Formally this means: s = (m - p) / p
 * Where m is the measurement, p is the prediction and s is the anomly score.
 *
 * @author Sören Henning
 *
 */
public class DeviationAnomalyScoreCalculator implements AnomalyScoreCalculator {

	@Override
	public double calculate(final double measurement, final double prediction) {

		if (Double.isNaN(prediction) && Double.isNaN(measurement)) {
			return 0.0d;
		} else if (Double.isNaN(prediction) || Double.isNaN(measurement)) {
			return 1.0d;
		} else {
			return (measurement - prediction) / prediction;
		}

	}

}
