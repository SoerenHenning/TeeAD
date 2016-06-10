package anomalydetection.forecast;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import anomalydetection.timeseries.EquidistantTimeSeries;
import anomalydetection.timeseries.TimeSeriesPoint;

public class RegressionForecaster implements Forecaster {

	@Override
	public double forecast(final EquidistantTimeSeries timeSeries) {
		final SimpleRegression regression = new SimpleRegression();

		for (TimeSeriesPoint point : timeSeries) {
			regression.addData(point.getTime().toEpochMilli(), point.getValue());
		}

		double prediction = regression.predict(timeSeries.getNextEndTime().toEpochMilli());
		// TODO Temp
		if (Double.isNaN(prediction)
				|| prediction < 0
				|| Double.isInfinite(prediction)) {
			prediction = 0;
		}
		return prediction;
	}

}
