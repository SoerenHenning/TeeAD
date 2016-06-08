package anomalydetection.timeseries;

import java.time.Duration;
import java.time.Instant;

public class EquidistantTimeSeries extends NewTimeSeries {

	private final Duration distance;

	private Instant begin;

	public EquidistantTimeSeries(final Duration distance, final Instant begin) {
		super();
		this.distance = distance;
		this.begin = begin;
	}

	public EquidistantTimeSeries(final Duration distance, final NewTimeSeries timeSeries) {
		super(timeSeries);
		this.distance = distance;
	}
	// TODO copy constructor

	public void appendBegin(final double value) {
		super.appendBegin(new TimeSeriesPoint(getNextBeginTime(), value));
	}

	public void appendEnd(final double value) {
		super.appendEnd(new TimeSeriesPoint(getNextEndTime(), value));
	}

	@Override
	public void appendBegin(final TimeSeriesPoint timeSeriesPoint) {
		if (!timeSeriesPoint.getTime().equals(getNextBeginTime())) {
			// TODO throw exception
		}
		super.appendBegin(timeSeriesPoint);
	}

	@Override
	public void appendEnd(final TimeSeriesPoint timeSeriesPoint) {
		if (!timeSeriesPoint.getTime().equals(getNextEndTime())) {
			// TODO throw exception
		}
		super.appendEnd(timeSeriesPoint);
	}

	@Override
	public TimeSeriesPoint removeBegin() {
		final TimeSeriesPoint point = super.removeBegin();
		if (isEmpty()) {
			this.begin = point.getTime();
		}
		return point;
	}

	@Override
	public TimeSeriesPoint removeEnd() {
		final TimeSeriesPoint point = super.removeEnd();
		if (isEmpty()) {
			this.begin = point.getTime();
		}
		return point;
	}

	public Duration getDistance() {
		return distance;
	}

	private Instant getNextBeginTime() {
		if (isEmpty()) {
			return this.begin;
		}
		return super.getEnd().getTime().plus(this.distance);
	}

	private Instant getNextEndTime() {
		if (isEmpty()) {
			return this.begin;
		}
		return super.getBegin().getTime().minus(this.distance);
	}

}
