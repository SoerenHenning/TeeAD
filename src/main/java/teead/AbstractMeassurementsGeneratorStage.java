package teead;

import teead.measurement.Measurement;
import teetime.framework.AbstractProducerStage;

abstract class AbstractMeassurementsGeneratorStage extends AbstractProducerStage<Measurement> {

	protected final MeasurementGenerator generator;
	protected final long generations;

	public AbstractMeassurementsGeneratorStage(final MeasurementGenerator generator, final long generations) {
		super();
		this.generator = generator;
		this.generations = generations;
	}

	@Override
	protected void execute() {

		for (int i = 0; i < this.generations; i++) {
			sendMeasurement();
		}

		this.terminateStage();
	}

	protected abstract void sendMeasurement();

}
