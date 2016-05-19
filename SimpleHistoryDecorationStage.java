package anomalydetection;

import teetime.stage.basic.AbstractTransformation;

public class SimpleHistoryDecorationStage extends AbstractTransformation<Measurement, Measurement> {

	@Override
	protected void execute(final Measurement measurement) {
		// TODO Auto-generated method stub
		TimeSeries history = new TimeSeries();
		history.append(new TimeSeriesPoint(measurement.getTime().minusSeconds(1), measurement.getValue() / 2));

		measurement.setHistory(history);

		this.outputPort.send(measurement);
	}

}
