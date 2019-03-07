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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Employee> employee;
 

    public Project() {
    }

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
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

}
