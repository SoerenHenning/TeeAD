package teead.forecast;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ForecastersTest {

	private static final String FORECASTERS_PATH = "teead.forecast";
	private static final String MEAN_FORECASTER_NAME = "MeanForecaster";
	private static final String LINEAR_WEIGHTED_FORECASTER_NAME = "LinearWeightedForecaster";
	private static final String LOGARITHMIC_WEIGHTED_FORECASTER_NAME = "LogarithmicWeightedForecaster";
	private static final String EXPONENTIAL_WEIGHTED_FORECASTER_NAME = "ExponentialWeightedForecaster";
	private static final String REGRESSION_FORECASTER_NAME = "RegressionForecaster";

	@Test
	public void testGetByClassNameWithoutFullClassName() {
		Forecaster forecaster = Forecasters.getByClassName(MEAN_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(MeanForecaster.class));
	}

	@Test
	public void testGetByClassNameWithFullClassName() {
		Forecaster forecaster = Forecasters.getByClassName(FORECASTERS_PATH + '.' + MEAN_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(MeanForecaster.class));
	}

	@Test
	public void testGetByFullClassNameWithMeanForecaster() {
		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + MEAN_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(MeanForecaster.class));
	}

	@Test
	public void testGetByFullClassNameWithLinearWeightedForecaster() {
		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + LINEAR_WEIGHTED_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(LinearWeightedForecaster.class));
	}

	@Test
	public void testGetByFullClassNameWithLogarithmicWeightedMeanForecaster() {
		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + LOGARITHMIC_WEIGHTED_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(LogarithmicWeightedForecaster.class));
	}

	@Test
	public void testGetByFullClassNameWithExponentialWeightedForecaster() {
		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + EXPONENTIAL_WEIGHTED_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(ExponentialWeightedForecaster.class));
	}

	@Test
	public void testGetByFullClassNameWithRegressionForecaster() {
		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + REGRESSION_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(RegressionForecaster.class));
	}

}
