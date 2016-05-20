package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.MeanAggregator;
import anomalydetection.forecast.MeanForecaster;
import anomalydetection.measurement.Measurement;
import teetime.framework.Configuration;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final SimpleForecastDecorationStage forecastDecorator = new SimpleForecastDecorationStage();
		final ForecastStage forecaster = new ForecastStage(new MeanForecaster());
		final Distributor<Measurement> measurementDistributor = new Distributor<>(new CopyByReferenceStrategy());
		final ExtractorStage extractor = new ExtractorStage(new TimeSeries());
		final NormalizerStage normalizerStage = new NormalizerStage(Duration.ofMinutes(1), new MeanAggregator());
		final PrinterStage printer = new PrinterStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), measurementDistributor.getInputPort());
		super.connectPorts(measurementDistributor.getNewOutputPort(), forecastDecorator.getInputPort());
		super.connectPorts(forecastDecorator.getOutputPort(), printer.getInputPort());
		super.connectPorts(measurementDistributor.getNewOutputPort(), extractor.getInputPort());
		super.connectPorts(extractor.getOutputPort(), normalizerStage.getInputPort());
		super.connectPorts(normalizerStage.getOutputPort(), forecaster.getInputPort());

	}

}
