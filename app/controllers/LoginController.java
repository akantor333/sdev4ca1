package controllers;

import play.mvc.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import models.employees.*;

import views.html.*;

public class LoginController extends Controller {
    private FormFactory formFactory;
 
    @Inject
    public LoginController(FormFactory f) {
        this.formFactory = f;
    }
    
    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(login.render(loginForm,Employee.getEmployeeById(session().get("Id"))));
}
public Result loginSubmit() {
    Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

    if (loginForm.hasErrors()) {
        return badRequest(login.render(loginForm,Employee.getEmployeeById(session().get("Id"))));
    } else {

        session().clear();

        session("Id", loginForm.get().getId());

        return redirect(controllers.routes.HomeController.index());
    }
}
public Result logout() {
    session().clear();
    flash("success", "You have been logged out");
    return redirect(routes.LoginController.login());
}

public Result registerEmployee() {
    Form<Employee> employeeForm = formFactory.form(Employee.class);
    return ok(registerEmployee.render(employeeForm,Employee.getEmployeeById(session().get("Id"))));
}

public Result registerEmployeeSubmit() {

    Form<Employee> newEmployeeForm = formFactory.form(Employee.class).bindFromRequest();

    if (newEmployeeForm.hasErrors()) {

        return badRequest(registerEmployee.render(newEmployeeForm,Employee.getEmployeeById(session().get("Id"))));
    } else {

        Employee  newEmployee = newEmployeeForm.get();

        if(Employee.getEmployeeById(newEmployee.getId())==null){
            newEmployee.save();
        }else{
            newEmployee.update();
        }
              
        
    flash("success", "Employee " + newEmployee.getFname() + " was registered.");

    return redirect(controllers.routes.LoginController.login()); 
    }
}



}