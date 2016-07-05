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
	private final String seriesId;

	private String seriesIdColumn = "series_id";
	private String timeColumn = "time";
	private String nanoColumn = "nanos";
	private String measurementColumn = "measurement";
	private String predictionColumn = "prediction";
	private String anomalyscoreColumn = "anomalyscore";

	public CassandraDriver(final Session session, final String table, final String seriesId) {
		this.session = session;
		this.table = table;
		this.seriesId = seriesId;

		if (session.getLoggedKeyspace() == null) {
			throw new IllegalArgumentException("No keyspace set.");
		}

		createTableIfNotExists();
	}

	public String getSeriesIdColumn() {
		return seriesIdColumn;
	}

	public void setSeriesIdColumn(final String seriesIdColumn) {
		this.seriesIdColumn = seriesIdColumn;
	}

	public String getTimeColumn() {
		return timeColumn;
	}

	public void setTimeColumn(final String timeColumn) {
		this.timeColumn = timeColumn;
	}

	public String getNanoColumn() {
		return nanoColumn;
	}

	public void setNanoColumn(final String nanoColumn) {
		this.nanoColumn = nanoColumn;
	}

	public String getMeasurementColumn() {
		return measurementColumn;
	}

	public void setMeasurementColumn(final String measurementColumn) {
		this.measurementColumn = measurementColumn;
	}

	public String getPredictionColumn() {
		return predictionColumn;
	}

	public void setPredictionColumn(final String predictionColumn) {
		this.predictionColumn = predictionColumn;
	}

	public String getAnomalyscoreColumn() {
		return anomalyscoreColumn;
	}

	public void setAnomalyscoreColumn(final String anomalyscoreColumn) {
		this.anomalyscoreColumn = anomalyscoreColumn;
	}

	@Override
	public TimeSeries retrieveTimeSeries(final Instant start, final Instant end) {
		// TODO Ignores nanos in Instants
		final Select statement = QueryBuilder.select(this.timeColumn, this.nanoColumn, this.measurementColumn)
				.from(this.table)
				.where(QueryBuilder.eq(this.seriesIdColumn, this.seriesId))
				.and(QueryBuilder.gte(this.timeColumn, start.toEpochMilli()))
				.and(QueryBuilder.lte(this.timeColumn, end.toEpochMilli()))
				.orderBy(QueryBuilder.asc(this.timeColumn));
		final ResultSet results = session.execute(statement);

		final TimeSeries timeSeries = new TimeSeries();

		for (Row row : results) {
			final Instant millisTime = row.getTimestamp(this.timeColumn).toInstant();
			final Instant time = millisTime.plusNanos(row.getInt(this.nanoColumn));
			final double value = row.getDouble(this.measurementColumn);
			timeSeries.appendEnd(new TimeSeriesPoint(time, value));
		}

		return timeSeries;
	}

	@Override
	public void storeMeasurement(final AnomalyScoredMeasurement measurement) {
		final Insert statement = QueryBuilder
				.insertInto(this.table)
				.value(this.seriesIdColumn, this.seriesId)
				.value(this.timeColumn, measurement.getTime().toEpochMilli())
				.value(this.nanoColumn, measurement.getTime().getNano() % 1_000_000)
				.value(this.measurementColumn, measurement.getValue())
				.value(this.predictionColumn, measurement.getPrediction())
				.value(this.anomalyscoreColumn, measurement.getAnomalyScore());
		session.execute(statement);
	}

	private void createTableIfNotExists() {
		session.execute(
				"CREATE TABLE IF NOT EXISTS " + this.table + " (" +
						this.seriesIdColumn + " text," +
						this.timeColumn + " timestamp," +
						this.nanoColumn + " int," +
						this.measurementColumn + " double," +
						this.predictionColumn + " double," +
						this.anomalyscoreColumn + " double," +
						"PRIMARY KEY (" + this.seriesIdColumn + ", " + this.timeColumn + ", " + this.nanoColumn + ")" +
						");");
	}

}
