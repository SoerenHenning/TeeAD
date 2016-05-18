package anomalydetection;

import java.util.ArrayList;
import java.util.List;

public class TimeSeries {

	private final List<TimeSeriesPoint> timeSeriesPoints = new ArrayList<TimeSeriesPoint>(); // todo java 8

	public void append(final TimeSeriesPoint timeSeriesPoint) {
		timeSeriesPoints.add(timeSeriesPoint);
	}

	public List<TimeSeriesPoint> getTimeSeriesPoints() {
		return timeSeriesPoints;
	}

}
