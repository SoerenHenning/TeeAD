package teead.aggregation;

import java.util.HashMap;

public class AggregatorConfiguration extends HashMap<String, String> {

	private static final int INITIAL_MAP_CAPACITY = 1; // Most times the configuration is empty

	public AggregatorConfiguration() {
		super(INITIAL_MAP_CAPACITY);
	}

	private static final long serialVersionUID = 1L;

}
