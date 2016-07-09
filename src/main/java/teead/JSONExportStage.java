package teead;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import teead.measurement.AnomalyScoredMeasurement;
import teetime.framework.AbstractConsumerStage;

public class JSONExportStage extends AbstractConsumerStage<AnomalyScoredMeasurement> {

	private final JsonGenerator jsonGenerator;

	public JSONExportStage(final File file) {

		try {
			JsonFactory jsonFactory = new JsonFactory();
			this.jsonGenerator = jsonFactory.createGenerator(file, JsonEncoding.UTF8);
			this.jsonGenerator.writeStartArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException(e);
		}

	}

	@Override
	protected void execute(final AnomalyScoredMeasurement measurement) {
		try {
			this.jsonGenerator.writeStartObject();
			writeNumberFieldOrNull("time", measurement.getTime().toEpochMilli());
			writeNumberFieldOrNull("measurement", measurement.getValue());
			writeNumberFieldOrNull("prediction", measurement.getPrediction());
			writeNumberFieldOrNull("anomalyscore", measurement.getAnomalyScore());
			this.jsonGenerator.writeEndObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void onTerminating() throws Exception {
		jsonGenerator.writeEndArray();
		jsonGenerator.close();

		super.onTerminating();
	}

	private void writeNumberFieldOrNull(final String key, final double value) throws IOException {
		if (Double.isFinite(value)) {
			this.jsonGenerator.writeNumberField(key, value);
		} else {
			this.jsonGenerator.writeNullField(key);
		}

	}
}
