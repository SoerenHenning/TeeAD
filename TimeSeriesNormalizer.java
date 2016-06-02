package anomalydetection;

import java.time.Duration;
import java.time.Instant;

import anomalydetection.aggregation.Aggregator;

public class TimeSeriesNormalizer {

	private final Duration stepSize;
	private final Aggregator aggregator;

	public TimeSeriesNormalizer(final Duration stepSize, final Aggregator aggregator) {
		this.stepSize = stepSize;
		this.aggregator = aggregator;
	}

	public TimeSeries normalize(final TimeSeries timeSeries) {

		if (timeSeries.getTimeSeriesPoints().isEmpty()) {
			return new TimeSeries();
		}

		TimeSeries equidistanteTimeSeries = new TimeSeries(); // TODO subclass

		// TOOD first(), peak(),...
		Instant intervalEnding = timeSeries.getTimeSeriesPoints().get(0).getTime().minus(this.stepSize);
		TimeSeries interval = new TimeSeries();

		for (TimeSeriesPoint point : timeSeries.getTimeSeriesPoints()) {
			if (point.getTime().isBefore(intervalEnding)) {
				// Aggregate Interval
				// add aggregated value to equistantTimeSeries
				double aggregated = aggregator.aggregate(interval);
				equidistanteTimeSeries.append(new TimeSeriesPoint(intervalEnding, aggregated));

				// Make new interval
				interval = new TimeSeries();
				intervalEnding = intervalEnding.minus(this.stepSize);
			}
			interval.append(point);
		}
		// letztes interval abschließen
		double aggregated = aggregator.aggregate(interval);
		equidistanteTimeSeries.append(new TimeSeriesPoint(intervalEnding, aggregated));
		// Redundater Code

		return equidistanteTimeSeries;

	}

}
