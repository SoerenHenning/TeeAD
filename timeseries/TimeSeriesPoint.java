package anomalydetection.timeseries;

import java.time.Instant;

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
