package anomalydetection;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

import anomalydetection.measurement.Measurement;

public class MeasurementGenerator {

	private Function<Double, Double> function;
	private double noise;
	private double anomalyProbability;
	private double anomayStrength;
	private Instant startTime;
	private Duration minStepDistance;
	private Duration maxStepDistance;

	private Instant time;
	private int index = 0;

	public MeasurementGenerator() {
		this.function = x -> 0.0;
		this.noise = 0;
		this.anomalyProbability = 0;
		this.anomayStrength = 0;
		this.startTime = Instant.now();
		this.minStepDistance = Duration.ofSeconds(1);
		this.maxStepDistance = Duration.ofSeconds(1);

		this.time = this.startTime;
	}

	public MeasurementGenerator(final Function<Double, Double> function, final double noise, final double anomalyProbability, final double anomayStrength,
			final Instant startTime,
			final Duration minStepDistance, final Duration maxStepDistance) {
		this.function = function;
		this.noise = noise;
		this.anomalyProbability = anomalyProbability;
		this.anomayStrength = anomayStrength;
		this.startTime = startTime;
		this.minStepDistance = minStepDistance;
		this.maxStepDistance = maxStepDistance;

		this.time = this.startTime;
	}

	public Function<Double, Double> getFunction() {
		return function;
	}

	public void setFunction(final Function<Double, Double> function) {
		this.function = function;
	}

	public double getNoise() {
		return noise;
	}

	public void setNoise(final double noise) {
		this.noise = noise;
	}

	public double getAnomalyProbability() {
		return anomalyProbability;
	}

	public void setAnomalyProbability(final double anomalyProbability) {
		this.anomalyProbability = anomalyProbability;
	}

	public double getAnomayStrength() {
		return anomayStrength;
	}

	public void setAnomayStrength(final double anomayStrength) {
		this.anomayStrength = anomayStrength;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(final Instant startTime) {
		this.startTime = startTime;
	}

	public void setStepDistance(final Duration stepDistance) {
		this.minStepDistance = stepDistance;
		this.maxStepDistance = stepDistance;
	}

	public Duration getMinStepDistance() {
		return minStepDistance;
	}

	public void setMinStepDistance(final Duration minStepDistance) {
		this.minStepDistance = minStepDistance;
	}

	public Duration getMaxStepDistance() {
		return maxStepDistance;
	}

	public void setMaxStepDistance(final Duration maxStepDistance) {
		this.maxStepDistance = maxStepDistance;
	}

	public void reset() {
		this.time = startTime;
		this.index = 0;
	}

	public Measurement getNext() {
		final double value = Math.max(0, calcFunctionValue() + calcNoise() + calcAnomaly());
		final Measurement measurement = new Measurement(this.time, value);

		this.index++;
		this.time = this.time.plus(calcStepDistance());

		return measurement;
	}

	private double calcFunctionValue() {
		return this.function.apply((double) index);
	}

	private double calcNoise() {
		// BETTER Should be the same
		// return ((Math.random() * 2) - 1) * this.noise;
		return (Math.random() * 2 * this.noise) - this.noise;
	}

	private double calcAnomaly() {
		if (Math.random() < this.anomalyProbability) {
			return this.anomayStrength * (Math.random() < 0.5 ? 1 : -1);
		} else {
			return 0;
		}
	}

	private Duration calcStepDistance() {
		final long minMillis = this.minStepDistance.toMillis();
		final long maxMillis = this.maxStepDistance.toMillis();
		final long randomMillis = minMillis + (long) (Math.random() * ((maxMillis - minMillis) + 1));
		return Duration.ofMillis(randomMillis);
	}
}
