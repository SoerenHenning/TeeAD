package anomalydetection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import kieker.common.record.flow.IFlowRecord;
import kieker.common.record.flow.trace.TraceMetadata;
import kieker.common.record.flow.trace.operation.AbstractOperationEvent;
import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.trace.operation.AfterOperationFailedEvent;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;

import anomalydetection.measurement.Measurement;
import teetime.stage.basic.AbstractTransformation;

public class KiekerAdapterStage extends AbstractTransformation<IFlowRecord, Measurement> {

	private final Map<Long, Trace> traces = new HashMap<>();

	@Override
	protected void execute(final IFlowRecord record) {
		if (record instanceof TraceMetadata) {
			this.handleMetadataRecord((TraceMetadata) record);
		} else if (record instanceof AbstractOperationEvent) {
			this.handleOperationEventRecord((AbstractOperationEvent) record);
		}
	}

	private void handleMetadataRecord(final TraceMetadata record) {
		// Create a new trace buffer for the new incoming trace
		final long traceID = record.getTraceId();
		final Trace trace = new Trace(record);

		this.traces.put(traceID, trace);
	}

	private void handleOperationEventRecord(final AbstractOperationEvent record) {
		// Check whether there was an incoming trace meta data record before
		if (this.traces.get(record.getTraceId()) != null) {
			if (record instanceof BeforeOperationEvent) {
				this.handleBeforeOperationEventRecord((BeforeOperationEvent) record);
			} else if (record instanceof AfterOperationEvent) {
				this.handleAfterOperationEventRecord((AfterOperationEvent) record);
			}
		} else {
			// TODO how to handle this?
		}
	}

	private void handleBeforeOperationEventRecord(final BeforeOperationEvent record) {
		this.traces.get(record.getTraceId()).push(record);
	}

	private void handleAfterOperationEventRecord(final AfterOperationEvent record) {
		final Trace trace = this.traces.get(record.getTraceId());
		final BeforeOperationEvent beforeEvent = trace.pop();

		if (record instanceof AfterOperationFailedEvent) {
			// TODO
		}

		// record.getOperationSignature();
		// record.getClassSignature();
		// long startTime = beforeEvent.getTimestamp();
		// long endTime = record.getTimestamp();
		// String hostname = trace.getHostname();

		// Additional log checks
		// if (TraceReconstructor.this.activateAdditionalLogChecks) {
		// if (!beforeEvent.getOperationSignature().equals(record.getOperationSignature())) {
		// TraceReconstructor.this.faultyTraceBuffers.add(this);
		// TraceReconstructor.this.traceBuffers.remove(this.traceID);
		// }
		// }

		// this.outputPort.send(element);

		if (traces.isEmpty()) {
			this.traces.remove(record.getTraceId());
		}
	}

	private class Trace {

		private final Deque<BeforeOperationEvent> buffer = new ArrayDeque<>();
		private final String hostname;

		public Trace(final TraceMetadata record) {
			this.hostname = record.getHostname();
		}

		public void push(final BeforeOperationEvent event) {
			this.buffer.push(event);
		}

		public BeforeOperationEvent pop() {
			return this.buffer.pop();
		}

		public String getHostname() {
			return hostname;
		}

	}

}
