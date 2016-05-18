package anomalydetection;

import anomalydetection.forecast.SimpleForecaster;
import teetime.framework.Configuration;

public class AnomalyDetectionConfiguration extends Configuration {

	public AnomalyDetectionConfiguration() {

		// Create the stages
		final MeassurementsGeneratorStage generator = new MeassurementsGeneratorStage();
		final SimpleHistoryDecorationStage historyDecorator = new SimpleHistoryDecorationStage();
		final ForecastStage forecaster = new ForecastStage(new SimpleForecaster());
		final PrinterStage printer = new PrinterStage();

		// Connect the stages
		super.connectPorts(generator.getOutputPort(), historyDecorator.getInputPort());
		super.connectPorts(historyDecorator.getOutputPort(), forecaster.getInputPort());
		super.connectPorts(forecaster.getOutputPort(), printer.getInputPort());

	}

}
