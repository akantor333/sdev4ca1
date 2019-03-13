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


import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;
import org.imgscalr.*;

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

@With(AuthAdmin.class)
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

        MultipartFormData<File> data = request().body().asMultipartFormData();
        FilePart<File> image = data.getFile("upload");
        String saveImageMessage = saveFile(newEmployee.getId(), image);
              
    flash("success", "Employee " + newEmployee.getFname() + " was registered, " +saveImageMessage);

    return redirect(controllers.routes.HomeController.index()); 
    }
}

public String saveFile(String id, FilePart<File> uploaded){
    if(uploaded != null){
        String mimeType = uploaded.getContentType();
        if(mimeType.startsWith("image/")){
            String fileName = uploaded.getFilename();
            String extension = "";
            int i = fileName.lastIndexOf('.');
            if(i >= 0){
                extension = fileName.substring(i+1);
            }
            File file = uploaded.getFile();

            File dir = new File("public/images/employeeImages");
            if(!dir.exists()){
                dir.mkdirs();
            }

            File newFile = new File("public/images/employeeImages/", id + "." + extension);
            if(file.renameTo(newFile)){
                try {
                    BufferedImage img = ImageIO.read(newFile);
                    BufferedImage scaledImg = Scalr.resize(img, 90);
                    if(ImageIO.write(scaledImg, extension , new File("public/images/employeeImages/", id + "thumb.jpg"))){
                        return "image uploaded and thumbnail created.";
                    }else{
                        return "image uploaded but thumbnail creation failed."; 
                    }
                }catch(IOException e){
                    return "image uploaded but thumbnail creation failed."; 
                }
            }else{
                return "image upload failed.";
            }
        }
    }
    return "no image file.";
}





}