package anomalydetection.anomalyscore;

public class SimpleAnomalyScoreCalculator implements IAnomalyScoreCalculator<Double> {

	@Override
	public double calculate(final Double value, final Double prediction) {
		// TODO
		return value + prediction;
	}

}
