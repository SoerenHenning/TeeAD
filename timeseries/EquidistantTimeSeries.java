package anomalydetection.timeseries;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

public class EquidistantTimeSeries extends NewTimeSeries {

	private final Duration distance;

	// private Instant start;
	// TODO Feld start, falls series leer

	public EquidistantTimeSeries(final Duration distance) {
		super();
		this.distance = distance;
	}

	public EquidistantTimeSeries(final Duration distance, final Collection<TimeSeriesPoint> timeSeriesPoints) {
		super(timeSeriesPoints);
		this.distance = distance;
	}

	@Override
	public void add(final TimeSeriesPoint point) {
		super.add(point);
	}

	public void add(final double value) {
		super.add(new TimeSeriesPoint(getNextTime(), value));
	}

	public Duration getDistance() {
		return distance;
	}

	private Instant getNextTime() {
		return this.timeSeriesPoints.getFirst().getTime().plus(this.distance);
	}

}
