package controllers;

import models.task.TaskHelper;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import javax.inject.Inject;

import static play.data.Form.form;

public class Application extends Controller {

    @Inject
    TaskHelper taskHelper;

    public Result index() {
        return ok(index.render(taskHelper.getAll()));
    }

    public Result createTask() {
        String label = form().bindFromRequest().get("label");
        taskHelper.create(label);
        return redirect(routes.Application.index());
    }
  
}
