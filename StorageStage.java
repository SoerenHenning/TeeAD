package anomalydetection;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

/**
 * Dummy stage that should later handles the storage of measurements
 *
 * @author Sören Henning
 *
 */
public class StorageStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		// Do Nothing
	}

}
