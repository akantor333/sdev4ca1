package controllers;

import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

import models.employees.*;

public class AuthAdmin extends Action.Simple {

    public CompletionStage<Result> call(Http.Context ctx) {

        String id = ctx.session().get("Id");
        if (id != null) {
            Employee u = Employee.getEmployeeById(id);
            if ("admin".equals(u.getPosition())) {
                return delegate.call(ctx);
            }
        }
        ctx.flash().put("error", "Administrator login required");
        return CompletableFuture.completedFuture(redirect(routes.LoginController.login()));
    }
}
