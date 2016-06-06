package anomalydetection.timeseries;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

public class BoundedTimeSeries extends NewTimeSeries {

	private final Duration capacity;

	public BoundedTimeSeries(final Duration capacity) {
		super();
		this.capacity = capacity;
	}

	public BoundedTimeSeries(final Duration capacity, final Collection<TimeSeriesPoint> timeSeriesPoints) {
		super(timeSeriesPoints);
		this.capacity = capacity;
		removeOverflow();
	}

	@Override
	public void add(final TimeSeriesPoint point) {
		super.add(point);
		removeOverflow();
	}

	public Duration getCapacity() {
		return capacity;
	}

	private void removeOverflow() {
		if (this.timeSeriesPoints.size() <= 0) {
			return;
		}
		final Instant firstTime = this.timeSeriesPoints.peek().getTime();
		while (timeSeriesPoints.size() > 0 && timeSeriesPoints.peekLast().getTime().isBefore(firstTime.minus(capacity))) {
			this.timeSeriesPoints.removeLast();
		}
	}
}
