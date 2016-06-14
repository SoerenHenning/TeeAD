package anomalydetection;

import anomalydetection.measurement.Measurement;
import anomalydetection.timeseries.BoundedTimeSeries;
import anomalydetection.timeseries.TimeSeries;
import anomalydetection.timeseries.TimeSeriesPoint;

//TODO Name has to be reconsidered
public class TimeSeriesExtractor {

	private final BoundedTimeSeries timeSeries; // TODO Bounded time Series

	public TimeSeriesExtractor(final BoundedTimeSeries timeSeries) {
		this.timeSeries = timeSeries;
	}

	public TimeSeries extract(final Measurement measurement) {

		final TimeSeries resultingTimeSeries = new TimeSeries(this.timeSeries);

		// TODO Filter just Points that are in investigation interval

		this.timeSeries.appendEnd(new TimeSeriesPoint(measurement.getTime(), measurement.getValue()));

		return resultingTimeSeries;
	}

}
