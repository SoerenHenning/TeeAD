package teead;

import java.time.Duration;
import java.time.Instant;

import teead.aggregation.Aggregator;
import teead.forecast.Forecaster;
import teead.storage.StorageAdapter;
import teead.timeseries.BoundedTimeSeries;

public class StorableAnomalyDetectionStage extends AnomalyDetectionStage {

	public StorableAnomalyDetectionStage(final Duration slidingWindowDuration, final Duration normalizationDuration, final Aggregator aggregator,
			final Forecaster forecaster, final StorageAdapter storageDriver) {
		super(new BoundedTimeSeries(slidingWindowDuration, storageDriver.retrieveTimeSeries(Instant.now().minus(slidingWindowDuration), Instant.now())),
				normalizationDuration, aggregator, forecaster);

		final StorageStage storageStage = new StorageStage(storageDriver);
		super.connectPorts(super.getNewOutputPort(), storageStage.getInputPort());
	}

}
