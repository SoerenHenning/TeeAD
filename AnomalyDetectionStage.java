package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.MeanAggregator;
import anomalydetection.forecast.WeightedForecaster;
import anomalydetection.forecast.WeightedForecaster.WeightMethod;
import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.measurement.Measurement;
import anomalydetection.timeseries.BoundedTimeSeries;
import teetime.framework.CompositeStage;
import teetime.framework.InputPort;
import teetime.framework.OutputPort;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

public class AnomalyDetectionStage extends CompositeStage {

	private final InputPort<Measurement> inputPort;

	final Distributor<AnomalyScoredMeasurement> anomalyScoreDistributor = new Distributor<>(new CopyByReferenceStrategy());

	public AnomalyDetectionStage() {

		// Create the stages
		final Distributor<Measurement> measurementDistributor = new Distributor<>(new CopyByReferenceStrategy());
		final ExtractorStage extractor = new ExtractorStage(new BoundedTimeSeries(Duration.ofHours(1)));
		final NormalizerStage normalizerStage = new NormalizerStage(Duration.ofSeconds(5), new MeanAggregator());
		final ForecastStage forecaster = new ForecastStage(new WeightedForecaster(WeightMethod.LINEAR));
		final MeasurementForecastDecorationStage measurementForecastDecorator = new MeasurementForecastDecorationStage();
		final AnomalyScoreCalculatorStage anomalyScoreCalculator = new AnomalyScoreCalculatorStage();
		final PrinterStage printer = new PrinterStage(); // TODO Temp
		final JSONExporter jsonExporter = new JSONExporter(); // TODO Temp
		final StorageStage storager = new StorageStage();

		this.inputPort = measurementDistributor.getInputPort();

		// Connect the stages
		super.connectPorts(measurementDistributor.getNewOutputPort(), extractor.getInputPort());
		super.connectPorts(extractor.getOutputPort(), normalizerStage.getInputPort());
		super.connectPorts(normalizerStage.getOutputPort(), forecaster.getInputPort());
		super.connectPorts(forecaster.getOutputPort(), measurementForecastDecorator.getInputPort1());
		super.connectPorts(measurementDistributor.getNewOutputPort(), measurementForecastDecorator.getInputPort2());
		super.connectPorts(measurementForecastDecorator.getOutputPort(), anomalyScoreCalculator.getInputPort());
		super.connectPorts(anomalyScoreCalculator.getOutputPort(), anomalyScoreDistributor.getInputPort());
		super.connectPorts(anomalyScoreDistributor.getNewOutputPort(), printer.getInputPort());
		super.connectPorts(anomalyScoreDistributor.getNewOutputPort(), jsonExporter.getInputPort());
		super.connectPorts(anomalyScoreDistributor.getNewOutputPort(), storager.getInputPort());

	}

	public OutputPort<AnomalyScoredMeasurement> getNewOutputPort(final double threshold) {
		return getNewOutputPort(new ThresholdFilter(threshold));
	}

	public OutputPort<AnomalyScoredMeasurement> getNewOutputPort(final double threshold, final ThresholdFilter.Comparator comparator) {
		return getNewOutputPort(new ThresholdFilter(threshold, comparator));
	}

	private OutputPort<AnomalyScoredMeasurement> getNewOutputPort(final ThresholdFilter thresholdFilter) {
		super.connectPorts(this.anomalyScoreDistributor.getNewOutputPort(), thresholdFilter.getInputPort());
		return thresholdFilter.getOutputPort();
	}

	public InputPort<Measurement> getInputPort() {
		return inputPort;
	}

}
