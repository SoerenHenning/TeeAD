package anomalydetection;

import anomalydetection.measurement.Measurement;
import anomalydetection.timeseries.BoundedTimeSeries;
import anomalydetection.timeseries.TimeSeries;
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
