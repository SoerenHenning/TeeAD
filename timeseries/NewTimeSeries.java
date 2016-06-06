package anomalydetection.timeseries;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class NewTimeSeries implements Iterable<TimeSeriesPoint> {

	protected final Deque<TimeSeriesPoint> timeSeriesPoints;

	public NewTimeSeries() {
		this.timeSeriesPoints = new ArrayDeque<>();
	}

	public NewTimeSeries(final Collection<TimeSeriesPoint> timeSeriesPoints) {
		this.timeSeriesPoints = new ArrayDeque<>(timeSeriesPoints);
	}

	public void add(final TimeSeriesPoint point) {
		this.timeSeriesPoints.push(point);
	}

	@Override
	// TODO check if own iterator is useful
	public Iterator<TimeSeriesPoint> iterator() {
		return this.timeSeriesPoints.iterator();
	}

	public int size() {
		return this.timeSeriesPoints.size();
	}

	@Override
	public String toString() {
		return this.timeSeriesPoints.toString();
	}
}
