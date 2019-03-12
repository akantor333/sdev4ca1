package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import models.employees.*;

@Entity
public class Department extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String name;


    public Department() {
    }

    public Department(Long id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
 


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployee() {
        return this.employees;
    }

    public void setEmployee(List<Employee> employee) {
        this.employees = employee;
    }

    public static Finder<Long,Department> find = new Finder<Long,Department>(Department.class);

    public static List<Department> findAll() {
   return Department.find.query().where().orderBy("name asc").findList();
    }

    public static Map<String,String> options() {
    LinkedHashMap<String,String> options = new LinkedHashMap();
 
    for (Department d: Department.findAll()) {
       options.put(d.getId().toString(), d.getName());
    }
    return options;
    }

}
