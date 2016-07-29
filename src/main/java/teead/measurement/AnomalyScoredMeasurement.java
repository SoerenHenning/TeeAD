package teead.measurement;

import java.time.Instant;

/**
 * A {@link AnomalyScoredMeasurement} is a {@link ForecastedMeassurement} with
 * an additional anomaly score.
 *
 * @author Sören Henning
 *
 */
public class AnomalyScoredMeasurement extends ForecastedMeassurement {

	private double anomalyScore;

	public AnomalyScoredMeasurement(final ForecastedMeassurement measurement, final double anomalyScore) {
		this(measurement.getTime(), measurement.getValue(), measurement.getPrediction(), anomalyScore);
	}

	public AnomalyScoredMeasurement(final Instant time, final double value, final double prediction, final double anomalyScore) {
		super(time, value, prediction);
		this.anomalyScore = anomalyScore;
	}

	public double getAnomalyScore() {
		return anomalyScore;
	}

	public void setAnomalyScore(final double anomalyScore) {
		this.anomalyScore = anomalyScore;
	}

}
