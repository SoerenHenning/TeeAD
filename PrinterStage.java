package anomalydetection;

import java.io.PrintStream;
import java.util.Formatter;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

public class PrinterStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	private final PrintStream stream;

	public PrinterStage() {
		this.stream = System.out;
	}

	public PrinterStage(final PrintStream stream) {
		this.stream = stream;
	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		Formatter formatter = new Formatter();
		formatter.format("T: %s | M: %6f | P: %6f | S: %6f", measurement.getTime(), measurement.getValue(), measurement.getPrediction(),
				measurement.getAnomalyScore());
		this.stream.println(formatter.toString());
		formatter.close();
	}

}
