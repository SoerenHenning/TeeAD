package anomalydetection.storage;

import java.time.Instant;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.timeseries.TimeSeries;

public class CassandraDriver implements StorageDriver {

	public CassandraDriver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TimeSeries retrieveTimeSeries(Instant start, Instant end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeMeasurement(AnomalyScoredMeasurement measurement) {
		// TODO Auto-generated method stub

	}

}
