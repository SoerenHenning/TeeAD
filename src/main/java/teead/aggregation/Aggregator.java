package teead.aggregation;

import teead.timeseries.TimeSeries;

/**
 * A {@link Aggregator} aggregates the values of a {@link TimeSeries} (or at
 * least a subset of them) to one value.
 *
 * Therefore classes that implement this interface have to implement the
 * {@link #aggregate(TimeSeries)} method.
 *
 * @author Sören Henning
 *
 */
public interface Aggregator {

	public double aggregate(final TimeSeries timeSeries);

}
