package teead.forecast;

import teead.timeseries.EquidistantTimeSeries;

/**
 * A forecaster predicts the next value for a given {@link EquidistantTimeSeries}.
 *
 * @author Sören Henning
 *
 */
public interface Forecaster {

	public double forecast(final EquidistantTimeSeries timeSeries);

}
