package anomalydetection;

import java.time.Duration;
import java.time.Instant;

import anomalydetection.aggregation.Aggregator;
import anomalydetection.timeseries.EquidistantTimeSeries;
import anomalydetection.timeseries.NewTimeSeries;
import anomalydetection.timeseries.TimeSeriesPoint;

public class TimeSeriesNormalizer {

	private final Duration stepSize;
	private final Aggregator aggregator;

	public TimeSeriesNormalizer(final Duration stepSize, final Aggregator aggregator) {
		this.stepSize = stepSize;
		this.aggregator = aggregator;
	}

	public EquidistantTimeSeries normalize(final NewTimeSeries timeSeries) {

		if (timeSeries.isEmpty()) {
			return new EquidistantTimeSeries(this.stepSize, Instant.MIN);
		}

		EquidistantTimeSeries equidistanteTimeSeries = new EquidistantTimeSeries(this.stepSize, timeSeries.getEnd().getTime());

		Instant intervalEnding = timeSeries.getEnd().getTime().minus(this.stepSize);
		NewTimeSeries interval = new NewTimeSeries();

		for (TimeSeriesPoint point : timeSeries.backwards()) {
			if (point.getTime().isBefore(intervalEnding)) {
				// Aggregate Interval
				// add aggregated value to equistantTimeSeries
				double aggregated = aggregator.aggregate(interval);
				equidistanteTimeSeries.appendBegin(aggregated);

				// Make new interval
				interval = new NewTimeSeries();
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
