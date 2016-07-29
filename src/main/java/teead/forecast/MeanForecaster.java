package teead.forecast;

import teead.timeseries.EquidistantTimeSeries;

/**
 * This class is a {@link Forecaster} that predicts a new value based on the
 * mean of the given {@link EquidistantTimeSeries}.
 *
 * @author Sören Henning
 *
 */
public class MeanForecaster implements Forecaster {

	@Override
	public double forecast(final EquidistantTimeSeries timeSeries) {

		return timeSeries.stream().mapToDouble(p -> p.getValue()).sum() / timeSeries.size();

	}

}
