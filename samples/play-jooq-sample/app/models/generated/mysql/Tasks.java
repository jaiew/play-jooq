/**
 * This class is generated by jOOQ
 */
package models.generated.mysql;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tasks extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -1373269979;

	/**
	 * The singleton instance of <code>tasks</code>
	 */
	public static final Tasks TASKS = new Tasks();

	/**
	 * No further instances allowed
	 */
	private Tasks() {
		super("tasks");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			models.generated.mysql.tables.Task.TASK);
	}
}
