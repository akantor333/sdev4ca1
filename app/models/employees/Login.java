package models.employees;


public class Login  {
    private String Id;
    private String password;

  
    public String validate() {
        if (Employee.authenticate(Id, password) == null) {
            return "Invalid ID or password";
        }
        return null;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}