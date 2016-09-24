package teead.forecast;

import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import teead.timeseries.EquidistantTimeSeries;

public abstract class AbstractRForecaster implements Forecaster {

	public final static String HOST_CONFIGURATION_KEY = "rserve.host";
	public final static String PORT_CONFIGURATION_KEY = "rserve.port";

	protected RConnection rConnection = null;
	private final String host;
	private final int port;

	public AbstractRForecaster(final String host, final int port) {
		this.host = host;
		this.port = port;
		establishRConnection();
	}

	public AbstractRForecaster(final ForecasterConfiguration configuration) {
		this(configuration.get(HOST_CONFIGURATION_KEY), Integer.parseInt(configuration.get(PORT_CONFIGURATION_KEY)));
	}

	private void establishRConnection() {
		// BETTER Do this asynchronously
		try {
			if (this.rConnection != null) {
				this.rConnection.close();
			}
			this.rConnection = new RConnection(this.host, this.port);
			this.rConnection.eval("library(forecast)");
		} catch (RserveException e) {
			// Could not establish the R connection
			this.rConnection = null;
		}
	}

	@Override
	public final double forecast(final EquidistantTimeSeries timeSeries) {
		if (this.rConnection != null) {
			try {
				return performRForecast(timeSeries);
			} catch (REngineException e) {
				establishRConnection();
				return Double.NaN;
			}
		} else {
			establishRConnection();
			return Double.NaN;
		}
	}

	protected abstract double performRForecast(final EquidistantTimeSeries timeSeries) throws REngineException;

}
