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
		return toArray(false);
	}

	public double[] toArray(final boolean withoutNaNs) {
		final int size = size(withoutNaNs);
		double[] array = new double[size];
		int i = 0;
		for (final TimeSeriesPoint point : this.timeSeriesPoints) {
			array[i] = point.getValue();
			i++;
		}
		return array;
	}

	public int size() {
		return size(false);
	}

	public int size(final boolean withoutNaNs) {
		int size = 0;
		if (withoutNaNs) {
			for (final TimeSeriesPoint point : this.timeSeriesPoints) {
				if (!Double.isNaN(point.getValue())) {
					size++;
				}
			}
		} else {
			size = this.timeSeriesPoints.size();
		}
		return size;
	}

}
