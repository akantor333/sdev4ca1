package models.employees;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;

@Entity
@Table (name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("e")

public class Employee extends Model {
    @Id
    private String Id;

    @Constraints.Required
    private String fname;

    @Constraints.Required
    private String sname;

    @Constraints.Required
    private String position;

    @Constraints.Required 
    private String email;

    @Constraints.Required 
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    @Constraints.Required
    private String password;

    @OneToOne(mappedBy="employee", cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Project> project;

    @ManyToOne
    private Department department;
 

    public static final Finder<Long, Employee> find = new Finder<>(Employee.class);

    public static Employee authenticate(String Id, String password) {
        return find.query().where().eq("Id", Id).eq("password", password).findUnique();
    }
    public static Employee getEmployeeById(String id) {
        if (id == null) {
            return null;
        } else {
            return find.query().where().eq("Id", id).findUnique();
        }
    }

    public static final List<Employee> findAll(){
        return Employee.find.all();
    }

    public Employee() {

    }
    public Employee(String Id, String fname, String sname, String position, String email, Date dob, String password, Address address, List<Project> project, Department department) {
        this.Id = Id;
        this.fname = fname;
        this.sname = sname;
        this.position = position;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.address = address;
        this.project = project;
        this.department = department;
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return this.sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Project> getProject() {
        return this.project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDobString(){
        return String.format("%1$td %1$tB %1$tY", dob);
    }

    public boolean isManager(Department d){
        if(d == department && position.contains("Manager")){
        return true;
    }else{
            return false;
        }
    }

    public void addProject(Project p){
        project.add(p);
    }
    
}