package teead.forecast;

public class LogarithmicWeightedForecaster extends AbstractWeightedForecaster {

	public LogarithmicWeightedForecaster() {
		super();
	}

	public LogarithmicWeightedForecaster(final ForecasterConfiguration configuration) {
		super(configuration);
	}

	@Override
	protected double getWeight(final int position, final int size) {
		return Math.log(position);
	}

}
