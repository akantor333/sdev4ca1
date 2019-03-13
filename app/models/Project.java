package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import models.employees.*;

@Entity
public class Project extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String name;

    @Column(columnDefinition = "TEXT")
    @Constraints.Required
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Employee> employee;
 

    public Project() {
    }

    public Project(Long id, String name, String description, List<Employee> employee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employee = employee;
    }

public String getDescription(){
    return this.description;
}

public void setDescription(String d){
    this.description = d;
}

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
        return this.employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public static Finder<Long,Project> find = new Finder<Long,Project>(Project.class);

    public static List<Project> findAll() {
   return Project.find.query().where().orderBy("name asc").findList();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap();
     
        for (Project d: Project.findAll()) {
           options.put(d.getId().toString(), d.getName());
        }
        return options;
        }
}
