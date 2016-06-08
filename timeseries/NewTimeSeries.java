package anomalydetection.timeseries;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class NewTimeSeries implements Iterable<TimeSeriesPoint> {

	private final Deque<TimeSeriesPoint> timeSeriesPoints;

	public NewTimeSeries() {
		this.timeSeriesPoints = new ArrayDeque<>();
	}

	public NewTimeSeries(final Collection<TimeSeriesPoint> timeSeriesPoints) {
		this.timeSeriesPoints = new ArrayDeque<>(timeSeriesPoints.size());
		for (TimeSeriesPoint timeSeriesPoint : timeSeriesPoints) {
			appendEnd(timeSeriesPoint);
		}
		// TODO Catch exception to provide new
	}

	public NewTimeSeries(final NewTimeSeries timeSeries) {
		this.timeSeriesPoints = timeSeries.timeSeriesPoints;
	}

	public void appendBegin(final TimeSeriesPoint timeSeriesPoint) {
		if (!this.timeSeriesPoints.isEmpty() && !timeSeriesPoint.getTime().isBefore(this.timeSeriesPoints.getFirst().getTime())) {
			// TODO throw expection
		}

		this.timeSeriesPoints.addFirst(timeSeriesPoint);
	}

	public void appendEnd(final TimeSeriesPoint timeSeriesPoint) {
		if (!this.timeSeriesPoints.isEmpty() && !timeSeriesPoint.getTime().isAfter(this.timeSeriesPoints.getLast().getTime())) {
			// TODO throw expection
		}

		this.timeSeriesPoints.addLast(timeSeriesPoint);
	}

	public TimeSeriesPoint getBegin() {
		return this.timeSeriesPoints.peekFirst();
	}

	public TimeSeriesPoint getEnd() {
		return this.timeSeriesPoints.peekLast();
	}

	public TimeSeriesPoint removeBegin() {
		return this.timeSeriesPoints.pollFirst();
	}

	public TimeSeriesPoint removeEnd() {
		return this.timeSeriesPoints.pollLast();
	}

	/**
	 * Returns an iterator over the time series points in this time series. The
	 * points will be returned in temporal order from the beginning of this
	 * time series to its ending.
	 */
	@Override
	public Iterator<TimeSeriesPoint> iterator() {
		return this.timeSeriesPoints.iterator();
	}

	/**
	 * Returns an iterator over the time series points in this time series. The
	 * points will be returned in temporal order from the ending of this
	 * time series to its beginning.
	 */
	public Iterator<TimeSeriesPoint> backwardsIterator() {
		return this.timeSeriesPoints.descendingIterator(); // TODO
	}

	/**
	 * Returns an iterable that iterates the points backwards. It is backed by
	 * the time series, so changes to the time series are reflected in the
	 * iterable, and vice-versa.
	 */
	public Iterable<TimeSeriesPoint> backwards() {
		return new Iterable<TimeSeriesPoint>() {
			@Override
			public Iterator<TimeSeriesPoint> iterator() {
				return backwardsIterator();
			}
		};
	}

	public int size() {
		return this.timeSeriesPoints.size();
	}

	public boolean isEmpty() {
		return this.timeSeriesPoints.isEmpty();
	}

	@Override
	public String toString() {
		return this.timeSeriesPoints.toString();
	}
}
