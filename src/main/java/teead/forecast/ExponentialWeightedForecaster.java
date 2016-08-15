package teead.forecast;

public class ExponentialWeightedForecaster extends AbstractWeightedForecaster {

	public ExponentialWeightedForecaster() {
		super();
	}

	public ExponentialWeightedForecaster(final ForecasterConfiguration configuration) {
		super(configuration);
	}

	@Override
	protected double getWeight(final int position, final int size) {
		// We need to do this scaling here to avoid overflows (Double.POSITIVE_INFINITY)
		return Math.exp(position - size);
	}

}
