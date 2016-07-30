package teead.storage;

import com.datastax.driver.core.Session;

/**
 * {@link CassandraManager} that holds a given instance of a Cassandra
 * {@link Session}, returns it on {@link #getSession()} and does nothing more.
 *
 * It is required that the {@link Session} has a set keyspace.
 *
 * @author Sören Henning
 *
 */
public class StaticInstanceCassandraManager implements CassandraManager {

	private final Session session;

	public StaticInstanceCassandraManager(final Session session) {
		if (session.getLoggedKeyspace() == null) {
			throw new IllegalArgumentException("No keyspace set.");
		}
		this.session = session;
	}

	@Override
	public Session getSession() {
		return this.session;
	}

}
