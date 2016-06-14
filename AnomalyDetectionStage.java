package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.Aggregator;
import anomalydetection.forecast.Forecaster;
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

	public AnomalyDetectionStage(final Duration slidingWindowDuration, final Duration normalizationDuration, final Aggregator aggregator,
			final Forecaster forecaster1) {

		// Create the stages
		final Distributor<Measurement> measurementDistributor = new Distributor<>(new CopyByReferenceStrategy());
		final ExtractorStage extractor = new ExtractorStage(new BoundedTimeSeries(slidingWindowDuration));
		final NormalizerStage normalizerStage = new NormalizerStage(normalizationDuration, aggregator);
		final ForecastStage forecaster = new ForecastStage(forecaster1);
		final MeasurementForecastDecorationStage measurementForecastDecorator = new MeasurementForecastDecorationStage();
		final AnomalyScoreCalculatorStage anomalyScoreCalculator = new AnomalyScoreCalculatorStage();
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
		super.connectPorts(anomalyScoreDistributor.getNewOutputPort(), storager.getInputPort());

	}

	public OutputPort<AnomalyScoredMeasurement> getNewOutputPort() {
		return this.anomalyScoreDistributor.getNewOutputPort();
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
