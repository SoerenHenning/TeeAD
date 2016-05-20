package anomalydetection;

import java.time.Instant;

import anomalydetection.aggregation.IAggregator;
import anomalydetection.aggregation.MeanAggregator;

public class TimeSeriesNormalizer {

	// TODO use java8 Duration
	private final long stepSize = 60 * 1000; // in ms -> 1 min
	private final IAggregator aggregator = new MeanAggregator(); // TODO temp

	public void normalize(final TimeSeries timeSeries) {

		TimeSeries equidistanteTimeSeries = new TimeSeries(); // TODO subclass

		// TOOD first(), peak(),...
		Instant intervalEnding = timeSeries.getTimeSeriesPoints().get(0).getTime().minusMillis(stepSize);
		TimeSeries interval = new TimeSeries();

		for (TimeSeriesPoint point : timeSeries.getTimeSeriesPoints()) {
			if (point.getTime().isBefore(intervalEnding)) {
				// Aggregate Interval
				// add aggregated value to equistantTimeSeries
				double aggregated = aggregator.aggregate(interval);
				equidistanteTimeSeries.append(new TimeSeriesPoint(intervalEnding, aggregated));

				// Make new interval
				interval = new TimeSeries();
				intervalEnding.minusMillis(stepSize); // TODO
			}
			interval.append(point);
		}
		// letztes interval abschließen

	}

}
