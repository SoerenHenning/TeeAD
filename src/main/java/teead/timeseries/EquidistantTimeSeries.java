package teead.timeseries;

import java.time.Duration;
import java.time.Instant;

public class EquidistantTimeSeries extends TimeSeries {

	private final Duration distance;

	private Instant begin;

	public EquidistantTimeSeries(final Duration distance, final Instant begin) {
		super();
		this.distance = distance;
		this.begin = begin;
	}

	public EquidistantTimeSeries(final Duration distance, final TimeSeries timeSeries) {
		super(timeSeries);
		this.distance = distance;
	}

	public EquidistantTimeSeries(final EquidistantTimeSeries timeSeries) {
		super(timeSeries);
		this.distance = timeSeries.distance;
		this.begin = timeSeries.begin;
	}

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
			throw new IllegalArgumentException("Point does not fit in time series. Expected time: " + getNextBeginTime());
		}
		super.appendBegin(timeSeriesPoint);
	}

	@Override
	public void appendEnd(final TimeSeriesPoint timeSeriesPoint) {
		if (!timeSeriesPoint.getTime().equals(getNextEndTime())) {
			// TODO throw exception
			throw new IllegalArgumentException("Point does not fit in time series. Expected time: " + getNextEndTime());
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

	public Instant getNextBeginTime() {
		return isEmpty() ? this.begin : super.getEnd().getTime().plus(this.distance);
	}

	public Instant getNextEndTime() {
		return isEmpty() ? this.begin : super.getBegin().getTime().minus(this.distance);
	}

}
