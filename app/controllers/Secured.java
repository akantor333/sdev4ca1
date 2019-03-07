package controllers;

import play.mvc.*;
import play.mvc.Http.*;


public class Secured extends Security.Authenticator {

    public String getId(Context ctx) {
        return ctx.session().get("Id");
    }

    public Result onUnauthorized(Context ctx) {
        return redirect(controllers.routes.LoginController.login());
    }
}