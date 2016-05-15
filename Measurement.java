package anomalydetection;

import java.time.Instant;

public class Measurement {

	private Instant time;

	private double value;

	private double forecastedValue;

	private double anomalyScore;

	private TimeSeries<Double> history;

	public Measurement(final Instant time, final double value) {
		this.time = time;
		this.value = value;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(final Instant time) {
		this.time = time;
	}

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	public double getForecastedValue() {
		return forecastedValue;
	}

	public void setForecastedValue(final double forecastedValue) {
		this.forecastedValue = forecastedValue;
	}

	public double getAnomalyScore() {
		return anomalyScore;
	}

	public void setAnomalyScore(final double anomalyScore) {
		this.anomalyScore = anomalyScore;
	}

	public TimeSeries<Double> getHistory() {
		return history;
	}

	public void setHistory(final TimeSeries<Double> history) {
		this.history = history;
	}

}
