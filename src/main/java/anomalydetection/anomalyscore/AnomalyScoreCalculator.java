package anomalydetection.anomalyscore;

public interface AnomalyScoreCalculator {

	public double calculate(final double value, final double prediction);

}
