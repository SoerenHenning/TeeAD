package anomalydetection;

import java.util.ArrayList;
import java.util.List;

public class TimeSeries<T> {

	private final List<TimeSeriesPoint<T>> timeSeriesPoints = new ArrayList<TimeSeriesPoint<T>>(); // todo java 8

	public void append(final TimeSeriesPoint<T> timeSeriesPoint) {
		timeSeriesPoints.add(timeSeriesPoint);
	}

	public List<TimeSeriesPoint<T>> getTimeSeriesPoints() {
		return timeSeriesPoints;
	}

}
