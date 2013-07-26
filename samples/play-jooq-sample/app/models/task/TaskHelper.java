package models.task;

import java.util.List;

/**
 * @author jaiew
 */
public interface TaskHelper {

    public Task create(String label);

    public List<Task> getAll();

}
