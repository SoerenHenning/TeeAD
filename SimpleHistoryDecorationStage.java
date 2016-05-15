package anomalydetection;

import teetime.stage.basic.AbstractTransformation;

public class SimpleHistoryDecorationStage extends AbstractTransformation<Measurement, Measurement> {

	@Override
	protected void execute(final Measurement measurement) {
		// TODO Auto-generated method stub
		TimeSeries<Double> history = new TimeSeries<Double>(); // TODO java 8
		history.append(new TimeSeriesPoint<Double>(measurement.getTime().minusSeconds(1), measurement.getValue() / 2));

		measurement.setHistory(history);

		this.outputPort.send(measurement);
	}

}
