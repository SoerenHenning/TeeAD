package teead;

import teead.measurement.Measurement;
import teead.timeseries.BoundedTimeSeries;
import teead.timeseries.TimeSeries;
import teead.timeseries.TimeSeriesPoint;

//TODO Name has to be reconsidered
public class TimeSeriesExtractor {

	private final BoundedTimeSeries timeSeries;

	public TimeSeriesExtractor(final BoundedTimeSeries timeSeries) {
		this.timeSeries = timeSeries;
	}

	public TimeSeries extract(final Measurement measurement) {

		final TimeSeries resultingTimeSeries = new TimeSeries(this.timeSeries);

		this.timeSeries.appendEnd(new TimeSeriesPoint(measurement.getTime(), measurement.getValue()));

		return resultingTimeSeries;
	}

}
