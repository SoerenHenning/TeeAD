package teead;

import teead.anomalyscore.AnomalyScoreCalculator;
import teead.anomalyscore.SimpleAnomalyScoreCalculator;
import teead.measurement.AnomalyScoredMeasurement;
import teead.measurement.ForecastedMeassurement;
import teetime.stage.basic.AbstractTransformation;

public class AnomalyScoreCalculatorStage extends AbstractTransformation<ForecastedMeassurement, AnomalyScoredMeasurement> {

	private final AnomalyScoreCalculator anomalyScoreCalculator;

	public AnomalyScoreCalculatorStage() {
		this.anomalyScoreCalculator = new SimpleAnomalyScoreCalculator();
	}

	public AnomalyScoreCalculatorStage(final AnomalyScoreCalculator anomalyScoreCalculator) {
		this.anomalyScoreCalculator = anomalyScoreCalculator;
	}

	@Override
	protected void execute(final ForecastedMeassurement forecastedMeassurement) {

		final double anomalyScore = anomalyScoreCalculator.calculate(forecastedMeassurement.getValue(), forecastedMeassurement.getPrediction());
		final AnomalyScoredMeasurement scoredMeasurement = new AnomalyScoredMeasurement(forecastedMeassurement, anomalyScore);
		this.outputPort.send(scoredMeasurement);
	}

}
