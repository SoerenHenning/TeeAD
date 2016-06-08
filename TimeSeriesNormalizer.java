package anomalydetection;

import java.time.Duration;
import java.time.Instant;

import anomalydetection.aggregation.Aggregator;
import anomalydetection.timeseries.EquidistantTimeSeries;
import anomalydetection.timeseries.TimeSeries;
import anomalydetection.timeseries.TimeSeriesPoint;

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
				// Aggregate Interval
				// add aggregated value to equistantTimeSeries
				double aggregated = aggregator.aggregate(interval);
				equidistanteTimeSeries.appendBegin(aggregated);

				// Make new interval
				interval = new TimeSeries();
				intervalEnding = intervalEnding.minus(this.stepSize);
			}
			interval.appendBegin(point);
		}
		// letztes interval abschließen
		double aggregated = aggregator.aggregate(interval);
		equidistanteTimeSeries.appendBegin(aggregated);
		// Redundater Code

		return equidistanteTimeSeries;

	}

}
