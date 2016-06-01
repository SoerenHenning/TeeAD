package anomalydetection.anomalyscore;

public class SimpleAnomalyScoreCalculator implements IAnomalyScoreCalculator {

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
