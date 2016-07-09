package teead.forecast;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import teead.timeseries.EquidistantTimeSeries;
import teead.timeseries.TimeSeriesPoint;

public class RegressionForecaster implements Forecaster {

	@Override
	public double forecast(final EquidistantTimeSeries timeSeries) {
		if (timeSeries.isEmpty()) {
			return 0; // TODO Double.NaN
		}

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
