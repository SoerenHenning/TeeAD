package teead;

import teead.measurement.Measurement;
import teead.timeseries.BoundedTimeSeries;
import teead.timeseries.TimeSeries;
import teetime.stage.basic.AbstractTransformation;

public class ExtractorStage extends AbstractTransformation<Measurement, TimeSeries> {

	private final TimeSeriesExtractor extractor;

	public ExtractorStage(final BoundedTimeSeries timeSeries) {
		this.extractor = new TimeSeriesExtractor(timeSeries);
	}

	@Override
	protected void execute(final Measurement measurement) {
		this.outputPort.send(this.extractor.extract(measurement));
	}

}
