package anomalydetection.forecast;

import org.apache.commons.math3.stat.StatUtils;

import anomalydetection.TimeSeries;

public class MeanForecaster implements IForecaster {

	@Override
	public double forecast(final TimeSeries timeSeries) {

		return StatUtils.mean(timeSeries.toArray());

	}

}
