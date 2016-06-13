package anomalydetection.forecast;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import anomalydetection.timeseries.EquidistantTimeSeries;

public abstract class AbstractRForecaster implements Forecaster {

	final RConnection rConnection;

	public AbstractRForecaster(final String host, final int port) {
		try {
			this.rConnection = new RConnection(host, port);
			this.rConnection.eval("library(forecast)");
		} catch (RserveException e) {
			// TODO Exception Handling
			e.printStackTrace();
			throw new IllegalStateException(e); // TODO
		}
	}

	@Override
	public final double forecast(final EquidistantTimeSeries timeSeries) {
		// TODO Exception Handling
		try {
			return performRForecast(timeSeries);
		} catch (REXPMismatchException | REngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Double.NaN; // TODO
	}

	protected abstract double performRForecast(final EquidistantTimeSeries timeSeries) throws REXPMismatchException, REngineException;

}
