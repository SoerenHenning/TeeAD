package anomalydetection;

import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final SimpleForecastDecorationStage forecastDecorator = new SimpleForecastDecorationStage();
		// final ForecastStage forecaster = new ForecastStage(new SimpleForecaster());
		final PrinterStage printer = new PrinterStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), forecastDecorator.getInputPort());
		super.connectPorts(forecastDecorator.getOutputPort(), printer.getInputPort());
		// super.connectPorts(forecaster.getOutputPort(), printer.getInputPort());

	}

}
