package teead.measurement;

import java.time.Instant;

/**
 * A {@link Measurement} consists of an instantaneous point on the time-line
 * {@code Instant} and the measured value.
 *
 * @author Sören Henning
 *
 */
public class Measurement {

	private Instant time;

	private double value;

	public Measurement(final Instant time, final double value) {
		this.time = time;
		this.value = value;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(final Instant time) {
		this.time = time;
	}

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

}
