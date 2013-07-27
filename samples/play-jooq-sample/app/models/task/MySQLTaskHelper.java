package models.task;

import models.PlayConnectionProvider;
import models.generated.mysql.Tables;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

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
        return create().selectFrom(Tables.TASK).fetch().into(Task.class);
    }

    private DSLContext create() {
        return DSL.using(new PlayConnectionProvider(), SQLDialect.MYSQL);
    }
}
