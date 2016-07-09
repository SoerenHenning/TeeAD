package teead;

import teead.measurement.AnomalyScoredMeasurement;
import teetime.stage.basic.AbstractFilter;

public class ThresholdFilter extends AbstractFilter<AnomalyScoredMeasurement> {

	public enum Comparator {
		GREATER, LOWER;
	}

	private final double threshold;
	private final Comparator comparator;

	public ThresholdFilter(final double threshold) {
		this.threshold = threshold;
		this.comparator = Comparator.GREATER;
	}

	public ThresholdFilter(final double threshold, final Comparator comparator) {
		this.threshold = threshold;
		this.comparator = comparator;
	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		switch (this.comparator) {
		case GREATER:
			if (measurement.getAnomalyScore() > this.threshold) {
				this.outputPort.send(measurement);
			}
			break;
		case LOWER:
			if (measurement.getAnomalyScore() < this.threshold) {
				this.outputPort.send(measurement);
			}
			break;
		default:
			break;
		}
	}

}
