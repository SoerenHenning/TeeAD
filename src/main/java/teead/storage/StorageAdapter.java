package teead.storage;

import java.time.Instant;

import teead.measurement.AnomalyScoredMeasurement;
import teead.timeseries.TimeSeries;

public interface StorageAdapter {

	public TimeSeries retrieveTimeSeries(final Instant start, final Instant end);

	public void storeMeasurement(final AnomalyScoredMeasurement measurement);

}
