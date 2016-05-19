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

	public double[] toArray() {
		double[] array = new double[this.timeSeriesPoints.size()];
		int i = 0;
		for (final TimeSeriesPoint point : this.timeSeriesPoints) {
			array[i] = point.getValue();
			i++;
		}
		return array;
	}

}
