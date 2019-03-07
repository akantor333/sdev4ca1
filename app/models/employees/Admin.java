package models.employees;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Table(name="employee")
@DiscriminatorValue("a")
@Entity
public class Admin extends Employee{
    public Admin(){}


    public Admin(String Id, String fname, String sname, String position, String email, Date dob, String password) {
        super(Id, fname, sname, position, email, dob, password);
    }


    public static final Finder<Long, Admin> find = new Finder<>(Admin.class);

    public static final List<Admin> findAll(){
        return Admin.find.all();
    }


}