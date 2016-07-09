package teead;

import java.io.PrintStream;

import teead.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

public class AlertPrinterStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	private final PrintStream stream;

	public AlertPrinterStage() {
		this.stream = System.out;
	}

	public AlertPrinterStage(final PrintStream stream) {
		this.stream = stream;
	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		this.stream.println("ALERT! Score: " + measurement.getAnomalyScore());
	}

}
