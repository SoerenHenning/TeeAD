package anomalydetection;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.storage.StorageDriver;
import teetime.framework.AbstractConsumerStage;

/**
 * Dummy stage that should later handles the storage of measurements
 *
 * @author Sören Henning
 *
 */
public class StorageStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	private final StorageDriver storageDriver;

	public StorageStage(final StorageDriver storageDriver) {
		this.storageDriver = storageDriver;
	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		storageDriver.storeMeasurement("temp", measurement); // TODO seriesid
	}

}
