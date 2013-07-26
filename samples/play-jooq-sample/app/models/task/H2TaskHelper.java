
package models.task;

import models.generated.h2.Tables;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import play.db.DB;

import java.sql.Connection;
import java.util.List;

/**
 * @author jaiew
 */
public class H2TaskHelper implements TaskHelper {

    @Override
    public Task create(String label) {
        return create().insertInto(Tables.TASK, Tables.TASK.LABEL)
                .values(label)
                .returning()
                .fetchOne()
                .into(Task.class);
    }

    @Override
    public List<Task> getAll() {
        return create().selectFrom(Tables.TASK).fetch().into(Task.class);
    }

    private DSLContext create() {
        Connection conn = DB.getConnection();
        return DSL.using(conn, SQLDialect.H2);
    }
}
