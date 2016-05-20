package anomalydetection;

import anomalydetection.measurement.Measurement;
import teetime.stage.basic.AbstractTransformation;

public class ExtractorStage extends AbstractTransformation<Measurement, TimeSeries> {

	private final TimeSeriesExtractor extractor;

	public ExtractorStage(final TimeSeries timeSeries) {
		this.extractor = new TimeSeriesExtractor(timeSeries);
	}

	@Override
	protected void execute(final Measurement measurement) {
		this.outputPort.send(this.extractor.extract(measurement));
	}

}
