package teead;

import java.time.Duration;
import java.time.Instant;

import teead.measurement.Measurement;

public class RealTimeMeasurementGeneratorStage extends AbstractMeassurementsGeneratorStage {

	private Instant lastTime = null;

	public RealTimeMeasurementGeneratorStage(final MeasurementGenerator generator, final long generations) {
		super(generator, generations);
	}

	@Override
	protected void sendMeasurement() {
		final Measurement measurement = this.generator.getNext();

		if (lastTime != null) {
			this.sleep(Duration.between(lastTime, measurement.getTime()));
		}

		this.outputPort.send(measurement);

		this.lastTime = measurement.getTime();
	}

	private void sleep(final Duration delay) {
		try {
			Thread.sleep(delay.toMillis());
		} catch (InterruptedException e) {
			this.terminateStage();
		}
	}

}
