package anomalydetection.aggregation;

import anomalydetection.TimeSeries;

public interface Aggregator {

	public double aggregate(final TimeSeries timeSeries);

}
