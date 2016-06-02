package anomalydetection;

import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final AnomalyDetectionStage anomalyDetector = new AnomalyDetectionStage();
		// final ForecastStage forecaster = new ForecastStage(new MeanForecaster());
		// final Distributor<Measurement> measurementDistributor = new Distributor<>(new CopyByReferenceStrategy());
		// final ExtractorStage extractor = new ExtractorStage(new TimeSeries());
		// final NormalizerStage normalizerStage = new NormalizerStage(Duration.ofMinutes(1), new MeanAggregator());
		// final MeasurementForecastDecorationStage measurementForecastDecorator = new MeasurementForecastDecorationStage();
		// final AnomalyScoreCalculatorStage anomalyScoreCalculator = new AnomalyScoreCalculatorStage();
		// final PrinterStage printer = new PrinterStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), anomalyDetector.getInputPort());
		// super.connectPorts(measurementDistributor.getNewOutputPort(), extractor.getInputPort());
		// super.connectPorts(extractor.getOutputPort(), normalizerStage.getInputPort());
		// super.connectPorts(normalizerStage.getOutputPort(), forecaster.getInputPort());
		// super.connectPorts(forecaster.getOutputPort(), measurementForecastDecorator.getInputPort1());
		// super.connectPorts(measurementDistributor.getNewOutputPort(), measurementForecastDecorator.getInputPort2());
		// super.connectPorts(measurementForecastDecorator.getOutputPort(), anomalyScoreCalculator.getInputPort());
		// super.connectPorts(anomalyScoreCalculator.getOutputPort(), printer.getInputPort());

	}

}
