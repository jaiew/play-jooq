package modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import models.task.MySQLTaskHelper;
import models.task.TaskHelper;

/**
 * @author jaiew
 */
public class MySQLModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(TaskHelper.class).to(MySQLTaskHelper.class);
    }
}
