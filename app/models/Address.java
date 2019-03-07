package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import models.employees.*;

@Entity
public class Address extends Model {
    @Id
    private Long id;

    @Constraints.Required
    private String nr;

    @Constraints.Required
    private String street;

    @Constraints.Required
    private String town;

    @Constraints.Required
    private String city;

    @Constraints.Required
    private String eircode;

    @OneToOne
    private Employee employee;


    public Address() {
    }


    public Address(Long id, String nr, String street, String town, String city, String eircode) {
        this.id = id;
        this.nr = nr;
        this.street = street;
        this.town = town;
        this.city = city;
        this.eircode = eircode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNr() {
        return this.nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEircode() {
        return this.eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
