package anomalydetection.anomalyscore;

public interface IAnomalyScoreCalculator {

	public double calculate(final double value, final double prediction);

}
