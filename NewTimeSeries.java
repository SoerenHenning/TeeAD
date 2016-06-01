package anomalydetection;

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
		this.timeSeriesPoints = new ArrayDeque<>(timeSeriesPoints);
	}

	@Override
	public Iterator<TimeSeriesPoint> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
