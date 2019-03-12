package controllers;

import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

import models.employees.*;
import models.*;

public class AuthAdmin extends Action.Simple {

    public CompletionStage<Result> call(Http.Context ctx) {

        String id = ctx.session().get("Id");
        if (id != null) {
            Employee u = Employee.getEmployeeById(id);
            Department d = u.getDepartment();
            if ("Administration".equals(d.getName())) {
                return delegate.call(ctx);
            }
        }
        ctx.flash().put("error", "Administrator login required");
        return CompletableFuture.completedFuture(redirect(routes.LoginController.login()));
    }
}
