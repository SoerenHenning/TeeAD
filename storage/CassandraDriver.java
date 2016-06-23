package anomalydetection.storage;

import java.time.Instant;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import anomalydetection.timeseries.TimeSeries;
import anomalydetection.timeseries.TimeSeriesPoint;

public class CassandraDriver implements StorageDriver {

	private final Session session;
	private final String table;

	public CassandraDriver(final Session session, final String table) {
		this.session = session;
		this.table = table;

		createTableIfNotExists();
	}

	@Override
	public TimeSeries retrieveTimeSeries(final String seriesId, final Instant start, final Instant end) {
		Select statement = QueryBuilder.select("time", "measurement")
				.from(this.table)
				.where(QueryBuilder.eq("series_id", seriesId))
				.and(QueryBuilder.gte("time", start.toEpochMilli()))
				.and(QueryBuilder.lte("time", end.toEpochMilli()))
				.orderBy(QueryBuilder.asc("time"));
		System.out.println(statement.toString());
		ResultSet results = session.execute(statement);

		TimeSeries timeSeries = new TimeSeries();

		for (Row row : results) {
			Instant time = row.getTimestamp("time").toInstant();
			double value = row.getDouble("measurement");
			timeSeries.appendEnd(new TimeSeriesPoint(time, value));
		}

		return timeSeries;
	}

	@Override
	public void storeMeasurement(final String seriesId, final AnomalyScoredMeasurement measurement) {
		final Insert statement = QueryBuilder
				.insertInto(this.table)
				.value("series_id", seriesId)
				.value("time", measurement.getTime().toEpochMilli())
				.value("measurement", measurement.getValue())
				.value("prediction", measurement.getPrediction())
				.value("anomalyscore", measurement.getAnomalyScore());
		session.execute(statement);
	}

	private void createTableIfNotExists() {
		session.execute(
				"CREATE TABLE IF NOT EXISTS " + this.table + " (" +
						"series_id text," +
						"time timestamp," +
						"measurement double," +
						"prediction double," +
						"anomalyscore double," +
						"PRIMARY KEY (series_id, time)" +
						");");
	}

}
