package anomalydetection;

import anomalydetection.anomalyscore.IAnomalyScoreCalculator;
import anomalydetection.anomalyscore.SimpleAnomalyScoreCalculator;
import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.measurement.ForecastedMeassurement;
import teetime.stage.basic.AbstractTransformation;

public class AnomalyScoreCalculatorStage extends AbstractTransformation<ForecastedMeassurement, AnomalyScoredMeasurement> {

	private final IAnomalyScoreCalculator anomalyScoreCalculator;

	public AnomalyScoreCalculatorStage() {
		this.anomalyScoreCalculator = new SimpleAnomalyScoreCalculator();
	}

	public AnomalyScoreCalculatorStage(final IAnomalyScoreCalculator anomalyScoreCalculator) {
		this.anomalyScoreCalculator = anomalyScoreCalculator;
	}

	@Override
	protected void execute(final ForecastedMeassurement forecastedMeassurement) {

		final double anomalyScore = anomalyScoreCalculator.calculate(forecastedMeassurement.getValue(), forecastedMeassurement.getPrediction());
		final AnomalyScoredMeasurement scoredMeasurement = new AnomalyScoredMeasurement(forecastedMeassurement, anomalyScore);
		this.outputPort.send(scoredMeasurement);
	}

}
