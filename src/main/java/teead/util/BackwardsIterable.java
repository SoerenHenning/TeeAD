package teead.util;

import java.util.Iterator;

/**
 * Implementing this interface allows an object to get iterated backwards. The
 * object returned by {@code backwards()} can be used by a "foreach" statement.
 *
 * @author Sören Henning
 *
 * @param <T>
 *            the type of elements returned by the iterator
 */
public interface BackwardsIterable<T> {

	/**
	 * Returns an Iterator that iterates the elements backwards.
	 */
	public Iterator<T> backwardsIterator();

	/**
	 * Returns an Iterable that iterates the elements backwards using the
	 * Iterator returned by {@code backwardsIterator()}.
	 */
	default public Iterable<T> backwards() {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return backwardsIterator();
			}
		};
	}

}
