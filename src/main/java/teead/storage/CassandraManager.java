package teead.storage;

import com.datastax.driver.core.Session;

/**
 * Classes that implements this interface must provide logic to return a
 * {@link Session} on a {@link #getSession()} method call.
 *
 * @author Sören Henning
 *
 */
@Deprecated
public interface CassandraManager {

	public Session getSession();

}
