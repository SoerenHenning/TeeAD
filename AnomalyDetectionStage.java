package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.MeanAggregator;
import anomalydetection.forecast.MeanForecaster;
import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.measurement.Measurement;
import teetime.framework.CompositeStage;
import teetime.framework.InputPort;
import teetime.framework.OutputPort;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

public class AnomalyDetectionStage extends CompositeStage {

	private final InputPort<Measurement> inputPort;

	public AnomalyDetectionStage() {

		// Create the stages
		// final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final ForecastStage forecaster = new ForecastStage(new MeanForecaster());
		final Distributor<Measurement> measurementDistributor = new Distributor<>(new CopyByReferenceStrategy());
		final ExtractorStage extractor = new ExtractorStage(new TimeSeries());
		final NormalizerStage normalizerStage = new NormalizerStage(Duration.ofMinutes(1), new MeanAggregator());
		final MeasurementForecastDecorationStage measurementForecastDecorator = new MeasurementForecastDecorationStage();
		final AnomalyScoreCalculatorStage anomalyScoreCalculator = new AnomalyScoreCalculatorStage();
		final PrinterStage printer = new PrinterStage();

		this.inputPort = measurementDistributor.getInputPort();

		// Connect the stages
		// super.connectPorts(generator.getOutputPort(), measurementDistributor.getInputPort());
		super.connectPorts(measurementDistributor.getNewOutputPort(), extractor.getInputPort());
		super.connectPorts(extractor.getOutputPort(), normalizerStage.getInputPort());
		super.connectPorts(normalizerStage.getOutputPort(), forecaster.getInputPort());
		super.connectPorts(forecaster.getOutputPort(), measurementForecastDecorator.getInputPort1());
		super.connectPorts(measurementDistributor.getNewOutputPort(), measurementForecastDecorator.getInputPort2());
		super.connectPorts(measurementForecastDecorator.getOutputPort(), anomalyScoreCalculator.getInputPort());
		super.connectPorts(anomalyScoreCalculator.getOutputPort(), printer.getInputPort());

	}

	// TODO Name
	public OutputPort<AnomalyScoredMeasurement> getNewOutputPort() {
		// Distributor in Instance
		// Distributor<AnomalyScoredMeasurement> distributor = new Distributor<>(new CopyByReferenceStrategy());
		// create new filter stage: Filter filter = new Filter(threshold, gt/lt);
		// distributor.getNewOutputPort() --> filter.getInputPort()
		// return filter.getOutputPort()
		return null; // TODO
	}

	public InputPort<Measurement> getInputPort() {
		return inputPort;
	}

}
