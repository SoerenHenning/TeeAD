package anomalydetection.forecast;

import anomalydetection.timeseries.EquidistantTimeSeries;

public class MeanForecaster implements Forecaster {

	@Override
	public double forecast(final EquidistantTimeSeries timeSeries) {

		return timeSeries.stream().mapToDouble(p -> p.getValue()).sum() / timeSeries.size();

	}

}
