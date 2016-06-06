package anomalydetection.forecast;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.timeseries.TimeSeries;

public class MeanForecaster implements Forecaster {

	@Override
	public double forecast(final TimeSeries timeSeries) {

		return StatUtils.mean(timeSeries.toArray(true));

	}

}
