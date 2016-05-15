package anomalydetection;

import java.time.Instant;

public class TimeSeriesPoint<T> {

	private final Instant time;

	private final T value;

	public TimeSeriesPoint(final Instant time, final T value) {
		this.time = time;
		this.value = value;
	}

	public Instant getTime() {
		return time;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "[" + this.time + "=" + this.value + "]";
	}

}
