package teead.forecast;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;

import teead.timeseries.EquidistantTimeSeries;

public class ARIMAForecaster extends AbstractRForecaster {

	public ARIMAForecaster(final String host, final int port) {
		super(host, port);
	}

	public ARIMAForecaster(final ForecasterConfiguration configuration) {
		super(configuration);
	}

	@Override
	protected double performRForecast(final EquidistantTimeSeries timeSeries) throws REngineException {

		if (timeSeries.isEmpty()) {
			return Double.NaN;
		}

		// Transfer time series to R
		super.rConnection.assign("timeSeries", timeSeries.toValuesArray());
		// Do ARIMA forecast on time Series
		super.rConnection.eval("forecast <- forecast(auto.arima(ts(timeSeries)), h=1)");
		// Retrieve result
		try {
			double result = super.rConnection.eval("as.numeric(forecast$mean)").asDouble();
			return result;
		} catch (REXPMismatchException e) {
			return Double.NaN;
		}

	}

}
