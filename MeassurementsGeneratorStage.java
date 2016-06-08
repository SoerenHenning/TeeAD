package anomalydetection;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

import anomalydetection.measurement.Measurement;
import teetime.framework.AbstractProducerStage;

public class MeassurementsGeneratorStage extends AbstractProducerStage<Measurement> {

	private final Function<Double, Double> function;
	private final double noise;
	private final double anomalyProbability;
	private final double anomayStrength;
	private final Duration stepDistance;
	private final long generations;

	public MeassurementsGeneratorStage(final Function<Double, Double> function, final double noise, final double anomalyProbability, final double anomayStrength,
			final Duration stepDistance, final long generations) {
		this.function = function;
		this.noise = noise;
		this.anomalyProbability = anomalyProbability;
		this.anomayStrength = anomayStrength;
		this.stepDistance = stepDistance;
		this.generations = generations;
	}

	@Override
	protected void execute() {
		final Instant now = Instant.now();
		for (int i = 0; i < this.generations; i++) {
			final Instant time = now.plus(stepDistance.multipliedBy(i));
			final double value = Math.max(0, transformToAnomaly(getFunctionValue(i) + getNoise()));
			final Measurement measurement = new Measurement(time, value);
			this.outputPort.send(measurement);
		}
		this.terminateStage();
	}

	private double getFunctionValue(final int x) {
		return this.function.apply((double) x);
	}

	private double getNoise() {
		return (Math.random() * 2 * this.noise) - this.noise;
	}

	private double transformToAnomaly(final double value) {
		if (Math.random() < this.anomalyProbability) {
			return value + anomayStrength * (Math.random() < 0.5 ? 1 : -1);
		} else {
			return value;
		}
	}

}
