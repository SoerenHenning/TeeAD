package anomalydetection;

import java.time.Duration;
import java.time.Instant;

import anomalydetection.aggregation.Aggregator;
import anomalydetection.forecast.Forecaster;
import anomalydetection.storage.StorageDriver;
import anomalydetection.timeseries.BoundedTimeSeries;

public class StorableAnomalyDetectionStage extends AnomalyDetectionStage {

	public StorableAnomalyDetectionStage(final Duration slidingWindowDuration, final Duration normalizationDuration, final Aggregator aggregator,
			final Forecaster forecaster, final StorageDriver storageDriver) {
		super(new BoundedTimeSeries(slidingWindowDuration, storageDriver.retrieveTimeSeries("abc", Instant.now().minus(slidingWindowDuration), Instant.now())),
				normalizationDuration, aggregator, forecaster);

		final StorageStage storageStage = new StorageStage(storageDriver);
		super.connectPorts(super.getNewOutputPort(), storageStage.getInputPort());
	}

}
