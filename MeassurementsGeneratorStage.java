package anomalydetection;

import java.time.Instant;

import anomalydetection.measurement.Measurement;
import teetime.framework.AbstractProducerStage;

public class MeassurementsGeneratorStage extends AbstractProducerStage<Measurement> {

	@Override
	protected void execute() {

		Instant now = Instant.now();

		for (int i = 0; i < 3600; i++) {
			Measurement measurement = new Measurement(now.plusSeconds(i), Math.random() * 1000);
			this.outputPort.send(measurement);
		}

		this.terminateStage();

	}

}
