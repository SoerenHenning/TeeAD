package teead.forecast;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ForecastersTest {

	private static final String FORECASTERS_PATH = "teead.forecast";
	private static final String MEAN_FORECASTER_NAME = "MeanForecaster";
	private static final String WEIGHTED_FORECASTER_NAME = "WeightedForecaster";
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
	public void testGetByFullClassNameWithWeigthedForecaster() {
		String weigthMethodName = WeightedForecaster.WeightMethod.LINEAR.name();
		ForecasterConfiguration configuration = new ForecasterConfiguration();
		configuration.put(WeightedForecaster.WEIGHT_METHOD_CONFIGURATION_KEY, weigthMethodName);

		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + WEIGHTED_FORECASTER_NAME, configuration);

		assertThat(forecaster, instanceOf(WeightedForecaster.class));
	}

	@Test
	public void testGetByFullClassNameWithRegressionForecaster() {
		Forecaster forecaster = Forecasters.getByFullClassName(FORECASTERS_PATH + '.' + REGRESSION_FORECASTER_NAME, new ForecasterConfiguration());

		assertThat(forecaster, instanceOf(RegressionForecaster.class));
	}

}
