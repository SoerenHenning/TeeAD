package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.IAggregator;
import teetime.stage.basic.AbstractTransformation;

public class NormalizerStage extends AbstractTransformation<TimeSeries, TimeSeries> {

	private final TimeSeriesNormalizer normalizer;

	public NormalizerStage(final IAggregator aggregator, final Duration stepSize) {
		this.normalizer = new TimeSeriesNormalizer();
	}

	@Override
	protected void execute(final TimeSeries timeSeries) {

		TimeSeries normalizedTimeSeries = this.normalizer.normalize(timeSeries);
		this.outputPort.send(normalizedTimeSeries);

	}

}
