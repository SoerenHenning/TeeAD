package teead;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import teead.measurement.Measurement;
import teead.timeseries.BoundedTimeSeries;
import teead.timeseries.TimeSeries;
import teead.timeseries.TimeSeriesPoint;

public class TimeSeriesExtractorTest {

	@Test
	public void testExtractFromEmptyTimeSeries() {

		BoundedTimeSeries timeSeries = new BoundedTimeSeries(Duration.ofMinutes(1));
		TimeSeriesExtractor timeSeriesExtractor = new TimeSeriesExtractor(timeSeries);

		Measurement measurement = new Measurement(Instant.parse("2016-08-26T10:00:00.00Z"), 42.24);

		TimeSeries extractedTimeSeries = timeSeriesExtractor.extract(measurement);

		assertTrue(extractedTimeSeries.size() == 0);
	}

	@Test
	public void testExtractFromThreeElementsTimeSeries() {

		BoundedTimeSeries timeSeries = new BoundedTimeSeries(Duration.ofMinutes(1));
		timeSeries.appendEnd(new TimeSeriesPoint(Instant.parse("2016-08-26T10:00:00.00Z"), 5));
		timeSeries.appendEnd(new TimeSeriesPoint(Instant.parse("2016-08-26T10:00:05.00Z"), 10));
		timeSeries.appendEnd(new TimeSeriesPoint(Instant.parse("2016-08-26T10:00:07.00Z"), 15));

		TimeSeriesExtractor timeSeriesExtractor = new TimeSeriesExtractor(timeSeries);

		Measurement measurement = new Measurement(Instant.parse("2016-08-26T10:00:10.00Z"), 20);

		TimeSeries extractedTimeSeries = timeSeriesExtractor.extract(measurement);

		assertTrue(extractedTimeSeries.size() == 3);
	}

}
