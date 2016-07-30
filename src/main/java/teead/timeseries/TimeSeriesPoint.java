package teead.timeseries;

import java.time.Instant;

/**
 * {@link TimeSeriesPoint}s are the elements of {@link TimeSeries}. They
 * consists of an instantaneous time stamp and a value.
 *
 * @author Sören Henning
 *
 */
public class TimeSeriesPoint {

	private final Instant time;

	private final double value;

	public TimeSeriesPoint(final Instant time, final double value) {
		this.time = time;
		this.value = value;
	}

	public Instant getTime() {
		return time;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "[" + this.time + "=" + this.value + "]";
	}

}
