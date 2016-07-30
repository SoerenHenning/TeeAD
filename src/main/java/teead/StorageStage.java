package teead;

import teead.measurement.AnomalyScoredMeasurement;
import teead.storage.StorageAdapter;
import teetime.framework.AbstractConsumerStage;

/**
 * This stage stores incoming {@code Measurement}s using a given {@code StorageDriver}.
 *
 * @author Sören Henning
 *
 */
public class StorageStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	private final StorageAdapter storageDriver;

	public StorageStage(final StorageAdapter storageDriver) {
		this.storageDriver = storageDriver;
	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		storageDriver.storeMeasurement(measurement);
	}

}
