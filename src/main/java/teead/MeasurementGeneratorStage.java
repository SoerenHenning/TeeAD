package teead;

import teead.measurement.Measurement;

public class MeasurementGeneratorStage extends AbstractMeassurementsGeneratorStage {

	public MeasurementGeneratorStage(final MeasurementGenerator generator, final long generations) {
		super(generator, generations);
	}

	@Override
	protected void sendMeasurement() {
		final Measurement measurement = this.generator.getNext();
		this.outputPort.send(measurement);
	}

}
