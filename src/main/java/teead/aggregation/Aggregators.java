package teead.aggregation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.google.common.base.Throwables;

/**
 * @author Sören Henning
 *
 */
public final class Aggregators {

	private static final String AGGREGATORS_PATH = "teead.aggregation";

	private Aggregators() {}

	public static Aggregator getByClassName(final String name, final AggregatorConfiguration configuration) {
		if (name.indexOf('.') < 0) {
			// It seems that just the class name were passed
			try {
				return getByFullClassName(AGGREGATORS_PATH + '.' + name, configuration);
			} catch (IllegalArgumentException exception) {
				return getByFullClassName(name, configuration);
			}
		} else {
			// It seems that a fully qualified name were passed
			try {
				return getByFullClassName(name, configuration);
			} catch (IllegalArgumentException exception) {
				return getByFullClassName(AGGREGATORS_PATH + '.' + name, configuration);
			}
		}
	}

	public static Aggregator getByFullClassName(final String name, final AggregatorConfiguration configuration) {

		try {
			// get aggregator class by using reflection
			Class<?> aggregatorClass = Class.forName(name);

			Class<?>[] constructorParameterClasses = new Class[] { AggregatorConfiguration.class };
			Object[] constructorParameterObjects = new Object[] { configuration };

			Constructor<?> constructor = aggregatorClass.getConstructor(constructorParameterClasses);

			return (Aggregator) constructor.newInstance(constructorParameterObjects);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("The Aggregator \""
					+ name
					+ "\" could not be found.", e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("The Aggregator \""
					+ name
					+ "\" is declared as abstract and cannot be instantiated", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("The constructor of \""
					+ name
					+ "\" could not be accessed.", e);
		} catch (IllegalArgumentException e) {
			// should not happen at all
			throw new IllegalArgumentException("The constructor of \""
					+ name
					+ "\" has not been called with the correct amount of arguments.", e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("The constructor of \""
					+ name
					+ "\" has thrown an exception:\n"
					+ Throwables.getStackTraceAsString(e), e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("The Aggregator \""
					+ name
					+ "\" does not have any constructor with exactly one AggregatorConfiguration as its parameter.", e);
		} catch (SecurityException e) {
			throw new IllegalArgumentException("A Security Manager is present and \""
					+ name
					+ "\"does not have the correct class loader.", e);
		}
	}

}
