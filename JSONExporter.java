package anomalydetection;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONWriter;

import anomalydetection.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

public class JSONExporter extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	private final JSONWriter writer;
	private final Writer baseWriter;

	public JSONExporter() {
		try {
			this.baseWriter = new FileWriter("values.json");
			this.writer = new JSONWriter(this.baseWriter);
			writer.array();
		} catch (JSONException | IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		try {
			writer.object();
			writer.key("time").value(measurement.getTime().toEpochMilli());
			writer.key("measurement").value(measurement.getValue());
			writer.key("prediction").value(measurement.getPrediction());
			writer.key("anomalyscore").value(measurement.getAnomalyScore());
			writer.endObject();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void onTerminating() throws Exception {

		writer.endArray();
		baseWriter.close();

		super.onTerminating();
	}

}
