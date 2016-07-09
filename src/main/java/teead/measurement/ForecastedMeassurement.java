package teead.measurement;

import java.time.Instant;

public class ForecastedMeassurement extends Measurement {

	private double prediction;

	public ForecastedMeassurement(final Measurement measurement, final double prediction) {
		this(measurement.getTime(), measurement.getValue(), prediction);
	}

	public ForecastedMeassurement(final Instant time, final double value, final double prediction) {
		super(time, value);
		this.prediction = prediction;
	}

	public double getPrediction() {
		return prediction;
	}

	public void setPrediction(final double prediction) {
		this.prediction = prediction;
	}

}
