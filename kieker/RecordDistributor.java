package anomalydetection.kieker;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

public class RecordDistributor extends AbstractConsumerStage<MonitoringRecord> {

	private final Map<RecordFilter, OutputPort<MonitoringRecord>> outputPorts = new HashMap<>();

	@Override
	protected void execute(final MonitoringRecord record) {

		for (Entry<RecordFilter, OutputPort<MonitoringRecord>> entry : outputPorts.entrySet()) {
			if (entry.getKey().test(record)) {
				entry.getValue().send(record);
			}
		}

	}

}
