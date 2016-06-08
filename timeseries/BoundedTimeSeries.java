package anomalydetection.timeseries;

import java.time.Duration;
import java.time.Instant;

public class BoundedTimeSeries extends NewTimeSeries {

	private final Duration capacity;

	public BoundedTimeSeries(final Duration capacity) {
		super();
		this.capacity = capacity;
	}

	public BoundedTimeSeries(final Duration capacity, final NewTimeSeries timeSeries) {
		super(timeSeries);
		this.capacity = capacity;
		removeOverflow();
	}

	public BoundedTimeSeries(final BoundedTimeSeries timeSeries) {
		super(timeSeries);
		this.capacity = timeSeries.getCapacity();
	}

	@Override
	public void appendBegin(final TimeSeriesPoint timeSeriesPoint) {
		super.appendBegin(timeSeriesPoint);
		removeOverflow();
	}

	@Override
	public void appendEnd(final TimeSeriesPoint timeSeriesPoint) {
		super.appendEnd(timeSeriesPoint);
		removeOverflow();
	}

	public Duration getCapacity() {
		return capacity;
	}

	private void removeOverflow() {
		if (this.timeSeriesPoints.size() == 0) {
			return;
		}
		final Instant limit = super.getEnd().getTime().minus(this.capacity);
		while (timeSeriesPoints.size() > 0 && super.getBegin().getTime().isBefore(limit)) {
			super.removeBegin();
		}
	}
}
