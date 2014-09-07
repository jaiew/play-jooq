package controllers;

import models.task.Task;
import models.task.TaskHelper;
import play.mvc.Controller;
import play.mvc.Result;
import scala.collection.JavaConverters;
import views.html.index;

import javax.inject.Inject;
import java.util.List;

import static play.data.Form.*;

public class Application extends Controller {

    @Inject
    TaskHelper taskHelper;

    public Result index() {
        List<Task> tasks = taskHelper.getAll();
        scala.collection.immutable.List<Task> convertedTasks =
                JavaConverters.asScalaBufferConverter(tasks).asScala().toList();
        return ok(index.render(convertedTasks));
    }

    public Result createTask() {
        String label = form().bindFromRequest().get("label");
        taskHelper.create(label);
        return redirect(routes.Application.index());
    }
  
}
