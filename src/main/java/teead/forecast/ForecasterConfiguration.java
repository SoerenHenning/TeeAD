package teead.forecast;

import java.util.HashMap;

public class ForecasterConfiguration extends HashMap<String, String> {

	private static final int INITIAL_MAP_CAPACITY = 1; // Most times the configuration is empty

	public ForecasterConfiguration() {
		super(INITIAL_MAP_CAPACITY);
	}

	private static final long serialVersionUID = 1L;

}
