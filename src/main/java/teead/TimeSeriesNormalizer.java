package teead;

import java.time.Duration;
import java.time.Instant;

import teead.aggregation.Aggregator;
import teead.timeseries.EquidistantTimeSeries;
import teead.timeseries.TimeSeries;
import teead.timeseries.TimeSeriesPoint;

public class TimeSeriesNormalizer {

	private final Duration stepSize;
	private final Aggregator aggregator;

	public TimeSeriesNormalizer(final Duration stepSize, final Aggregator aggregator) {
		this.stepSize = stepSize;
		this.aggregator = aggregator;
	}

	public EquidistantTimeSeries normalize(final TimeSeries timeSeries) {

		if (timeSeries.isEmpty()) {
			return new EquidistantTimeSeries(this.stepSize, Instant.MIN);
		}

		EquidistantTimeSeries equidistanteTimeSeries = new EquidistantTimeSeries(this.stepSize, timeSeries.getEnd().getTime());

		Instant intervalEnding = timeSeries.getEnd().getTime().minus(this.stepSize);
		TimeSeries interval = new TimeSeries();

		for (TimeSeriesPoint point : timeSeries.backwards()) {
			if (point.getTime().isBefore(intervalEnding)) {
				// Aggregate Interval add aggregated value to equistantTimeSeries
				double aggregated = aggregator.aggregate(interval);
				equidistanteTimeSeries.appendBegin(aggregated);

				// Make new interval
				interval = new TimeSeries();
				intervalEnding = intervalEnding.minus(this.stepSize);
			}
			interval.appendBegin(point);
		}
		// Finish last interval
		double aggregated = aggregator.aggregate(interval);
		equidistanteTimeSeries.appendBegin(aggregated);

		return equidistanteTimeSeries;

	}

}
