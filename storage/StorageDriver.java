package anomalydetection.storage;

import java.time.Instant;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.timeseries.TimeSeries;

public interface StorageDriver {

	public TimeSeries retrieveTimeSeries(final String seriesid, final Instant start, final Instant end);

	public void storeMeasurement(final String seriesid, final AnomalyScoredMeasurement measurement);

}
