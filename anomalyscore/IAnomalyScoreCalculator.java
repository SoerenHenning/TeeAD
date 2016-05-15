package anomalydetection.anomalyscore;

public interface IAnomalyScoreCalculator<T> {

	public double calculate(final T value, final T prediction);

}
