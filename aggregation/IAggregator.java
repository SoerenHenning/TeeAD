package anomalydetection.aggregation;

import anomalydetection.TimeSeries;

public interface IAggregator {

	public double aggregate(final TimeSeries timeSeries);

}
