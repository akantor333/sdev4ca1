package controllers;

import play.mvc.*;

import views.html.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import models.*;
import models.employees.*;

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;

 
public class HomeController extends Controller {

    private FormFactory formFactory;
    private Environment e;

    @Inject
    public HomeController(FormFactory f, Environment env) {
        this.e = env;
        this.formFactory = f;
}


    public Result index() {

    return ok(index.render(Employee.getEmployeeById(session().get("Id"))));

     }

     @Security.Authenticated(Secured.class)
     public Result employees(){

        List<Employee> employeeList = null;
        employeeList = Employee.findAll();

        return ok(employees.render(employeeList,Employee.getEmployeeById(session().get("Id"))));  
     }
     @Security.Authenticated(Secured.class)
     public Result departments(){

        List<Department> departmentList = null;
        departmentList = Department.findAll();

        return ok(departments.render(departmentList,Employee.getEmployeeById(session().get("Id"))));  
     }
     @Security.Authenticated(Secured.class)
     public Result projects(){

        List<Project> projectList = null;
        projectList = Project.findAll();

        return ok(projects.render(projectList,Employee.getEmployeeById(session().get("Id"))));  
     }
     @Security.Authenticated(Secured.class)
     public Result individualDepartment(Long id){

        Department department = null;
        department = Department.find.byId(id);

        List<Employee> employeeList = null;
        employeeList = Employee.findAll();

        return ok(individualDepartment.render(department,employeeList,Employee.getEmployeeById(session().get("Id"))));  
     }
     @Security.Authenticated(Secured.class)
     public Result individualEmployee(String id){

        Employee emp = null;
        emp = emp.getEmployeeById(id);

        List<Project> projectList = null;
        projectList = emp.getProject();

        List<Project> allProjects = null;
        allProjects = Project.findAll();

        Address address = null;
        address = emp.getAddress();

        return ok(individualEmployee.render(address, emp,allProjects,projectList,e,Employee.getEmployeeById(session().get("Id"))));  
     }
     @Security.Authenticated(Secured.class)
     public Result individualProject(Long id){

        Project project = null;
        project = Project.find.byId(id);

        List<Employee> employee = null;
        employee = project.getEmployee();

        
        return ok(individualProject.render(project,employee,Employee.getEmployeeById(session().get("Id"))));  
     }


     @Security.Authenticated(Secured.class)
     @Transactional
     public Result addProjectToEmployee(String eid, Long pid){
         Employee emp = Employee.getEmployeeById(eid);
         Project pro = Project.find.byId(pid);

         emp.addProjectToEmployee(pro);
         emp.update();

         return redirect(controllers.routes.HomeController.individualEmployee(eid)); 
     }
    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result editEmployee(String id) {
    Employee i;
    Form<Employee> employeeForm;

    try {
        i = Employee.getEmployeeById(id);

        employeeForm = formFactory.form(Employee.class).fill(i);
    } catch (Exception ex) {
        return badRequest("error");
    }

    return ok(registerEmployee.render(employeeForm,Employee.getEmployeeById(session().get("Id"))));
}
@Security.Authenticated(Secured.class)
@Transactional
public Result updateAddress(String id) {
    Employee emp = Employee.getEmployeeById(id);
    Form<Address> addressForm = formFactory.form(Address.class);
    return ok(updateAddress.render(addressForm,emp,Employee.getEmployeeById(session().get("Id"))));
}

@Security.Authenticated(Secured.class)
public Result editAddress(String id,Long aid) {

    Employee emp = Employee.getEmployeeById(id);
    Address i;
    Form<Address> employeeForm;

    try {
        i = Address.find.byId(aid);

        employeeForm = formFactory.form(Address.class).fill(i);
    } catch (Exception ex) {
        return badRequest("error");
    }

    return ok(updateAddress.render(employeeForm,emp,Employee.getEmployeeById(session().get("Id"))));
}

@Security.Authenticated(Secured.class)
@Transactional
public Result updateAddressSubmit(String id) {

    Form<Address> newAddressForm = formFactory.form(Address.class).bindFromRequest();
    Employee emp = Employee.getEmployeeById(id);

    if (newAddressForm.hasErrors()) {

        return badRequest(updateAddress.render(newAddressForm,emp,Employee.getEmployeeById(session().get("Id"))));
    } else {

        Address  newAddress = newAddressForm.get();
        emp.setAddress(newAddress);
        newAddress.save();
        emp.update();


              
    flash("success", "Address was Updated");

    return redirect(controllers.routes.HomeController.individualEmployee(id)); 
    }
}

@Security.Authenticated(Secured.class)
@Transactional
@With(AuthAdmin.class)
public Result deleteDepartment(Long id) {

    Department.find.ref(id).delete();

    flash("success", "Department has been deleted.");
    return redirect(controllers.routes.HomeController.departments());
}

@Security.Authenticated(Secured.class)
@Transactional
@With(AuthAdmin.class)
public Result deleteEmployee(String id) {

    Employee.getEmployeeById(id).delete();

    flash("success", "Employee has been deleted.");
    return redirect(controllers.routes.HomeController.employees());
}

@Security.Authenticated(Secured.class)
@Transactional
@With(AuthAdmin.class)
public Result deleteProject(Long id) {

    Project.find.ref(id).delete();

    flash("success", "Project has been deleted.");
    return redirect(controllers.routes.HomeController.projects());
}

@Security.Authenticated(Secured.class)
@Transactional
public Result addDepartment() {
    Form<Department> departmentForm = formFactory.form(Department.class);
    return ok(addDepartment.render(departmentForm,Employee.getEmployeeById(session().get("Id"))));
}

@Security.Authenticated(Secured.class)
@Transactional
public Result addProject() {
    Form<Project> projectForm = formFactory.form(Project.class);
    return ok(addProject.render(projectForm,Employee.getEmployeeById(session().get("Id"))));
}

@Security.Authenticated(Secured.class)
public Result addProjectSubmit() {

    Form<Project> newProjectForm = formFactory.form(Project.class).bindFromRequest();

    if (newProjectForm.hasErrors()) {

        return badRequest(addProject.render(newProjectForm,Employee.getEmployeeById(session().get("Id"))));
    } else {

        Project  newProject = newProjectForm.get();
        newProject.save();

              
    flash("success", "Project was added");

    return redirect(controllers.routes.HomeController.projects()); 
    }
}
@Security.Authenticated(Secured.class)
@Transactional
@With(AuthAdmin.class)
public Result addDepartmentSubmit() {

    Form<Department> newDepartmentForm = formFactory.form(Department.class).bindFromRequest();

    if (newDepartmentForm.hasErrors()) {

        return badRequest(addDepartment.render(newDepartmentForm,Employee.getEmployeeById(session().get("Id"))));
    }else {

        Department  newDepartment = newDepartmentForm.get();
        newDepartment.save();
        }

              
        flash("success", "Department was added");

    return redirect(controllers.routes.HomeController.departments()); 
    }
}


