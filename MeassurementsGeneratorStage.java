package anomalydetection;

import java.time.Instant;

import teetime.framework.AbstractProducerStage;

public class MeassurementsGeneratorStage extends AbstractProducerStage<Measurement> {

	@Override
	protected void execute() {

		for (int i = 0; i < 1000; i++) {
			Measurement measurement = new Measurement(Instant.now().plusSeconds(i), Math.random());
			this.outputPort.send(measurement);
		}

		this.terminate();

	}

}
