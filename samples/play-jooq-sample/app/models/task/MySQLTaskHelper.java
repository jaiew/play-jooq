package models.task;

import models.generated.mysql.Tables;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import play.db.DB;

import java.util.List;

/**
 * @author jaiew
 */
public class MySQLTaskHelper implements TaskHelper {

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
        return create().selectFrom(models.generated.mysql.Tables.TASK).fetch().into(Task.class);
    }

    private DSLContext create() {
        return DSL.using(DB.getConnection(), SQLDialect.MYSQL);
    }
}
