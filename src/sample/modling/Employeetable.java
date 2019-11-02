package sample.modling;

public class Employeetable {

    private int Employee_id;
    private String Employee_Name;
    private String Username;
    private String Password;
    private String Type;
    private String Salary;
    private String Phone;
    private String Address;

    public Employeetable(int employee_id, String employee_Name, String username, String password, String type, String salary, String phone, String address) {

        Employee_id = employee_id;
        Employee_Name = employee_Name;
        Username = username;
        Password = password;
        Type = type;
        Salary = salary;
        Phone = phone;
        Address = address;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int employee_id) {
        Employee_id = employee_id;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
