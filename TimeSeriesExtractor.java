package anomalydetection;

import anomalydetection.measurement.Measurement;
import anomalydetection.timeseries.TimeSeries;
import anomalydetection.timeseries.TimeSeriesPoint;

//TODO Name has to be reconsidered
public class TimeSeriesExtractor {

	private final TimeSeries timeSeries; // TODO Bounded time Series

	public TimeSeriesExtractor(final TimeSeries timeSeries) {
		this.timeSeries = timeSeries;
	}

	public TimeSeries extract(final Measurement measurement) {
		TimeSeries timeSeries = new TimeSeries();

		// TODO Filter just Points that are in investigation interval

		for (TimeSeriesPoint timeSeriesPoint : this.timeSeries.getTimeSeriesPoints()) {
			timeSeries.append(timeSeriesPoint);
		}

		this.timeSeries.append(new TimeSeriesPoint(measurement.getTime(), measurement.getValue()));

		return timeSeries;
	}

}
