package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.IAggregator;
import teetime.stage.basic.AbstractTransformation;

public class NormalizerStage extends AbstractTransformation<TimeSeries, TimeSeries> {

	private final TimeSeriesNormalizer normalizer;

	public NormalizerStage(final Duration stepSize, final IAggregator aggregator) {
		this.normalizer = new TimeSeriesNormalizer(stepSize, aggregator);
	}

	@Override
	protected void execute(final TimeSeries timeSeries) {

		TimeSeries normalizedTimeSeries = this.normalizer.normalize(timeSeries);
		this.outputPort.send(normalizedTimeSeries);

	}

}
