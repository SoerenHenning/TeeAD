package teead.anomalyscore;

/**
 * Classes that implement this interface have to implement the
 * {@link #calculate(double, double)} method that calculates an anomaly score
 * based on an actual and a predicted value.
 *
 * @author Sören Henning
 *
 */
public interface AnomalyScoreCalculator {

	public double calculate(final double value, final double prediction);

}
