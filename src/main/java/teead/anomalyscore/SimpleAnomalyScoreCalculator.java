package teead.anomalyscore;

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
