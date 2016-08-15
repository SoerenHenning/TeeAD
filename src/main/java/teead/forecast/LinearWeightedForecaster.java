package teead.forecast;

public class LinearWeightedForecaster extends AbstractWeightedForecaster {

	public LinearWeightedForecaster() {
		super();
	}

	public LinearWeightedForecaster(final ForecasterConfiguration configuration) {
		super(configuration);
	}

	@Override
	protected double getWeight(final int position, final int size) {
		return position;
	}

}
