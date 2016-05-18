package anomalydetection.anomalyscore;

public class SimpleAnomalyScoreCalculator implements IAnomalyScoreCalculator {

	@Override
	public double calculate(final double value, final double prediction) {
		// TODO
		return value + prediction;
	}

}
