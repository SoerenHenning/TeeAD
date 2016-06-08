package anomalydetection;

import java.time.Duration;

import anomalydetection.aggregation.Aggregator;
import anomalydetection.timeseries.EquidistantTimeSeries;
import anomalydetection.timeseries.NewTimeSeries;
import teetime.stage.basic.AbstractTransformation;

public class NormalizerStage extends AbstractTransformation<NewTimeSeries, EquidistantTimeSeries> {

	private final TimeSeriesNormalizer normalizer;

	public NormalizerStage(final Duration stepSize, final Aggregator aggregator) {
		this.normalizer = new TimeSeriesNormalizer(stepSize, aggregator);
	}

	@Override
	protected void execute(final NewTimeSeries timeSeries) {

		EquidistantTimeSeries normalizedTimeSeries = this.normalizer.normalize(timeSeries);
		this.outputPort.send(normalizedTimeSeries);

	}

}
