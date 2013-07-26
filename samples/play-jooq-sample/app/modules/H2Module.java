
package modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import models.task.H2TaskHelper;
import models.task.TaskHelper;

/**
 * @author jaiew
 */
public class H2Module implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(TaskHelper.class).to(H2TaskHelper.class);
    }

}
