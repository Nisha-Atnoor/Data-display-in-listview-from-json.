package com.task.taskgo;

public class Employee {
    public String eName,eId,eAge,eSalary;

    public Employee(String eName, String eId, String eAge, String eSalary) {
        this.eName = eName;
        this.eId = eId;
        this.eAge = eAge;
        this.eSalary = eSalary;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteAge() {
        return eAge;
    }

    public void seteAge(String eAge) {
        this.eAge = eAge;
    }

    public String geteSalary() {
        return eSalary;
    }

    public void setSalary(String eSalary) {
        this.eSalary = eSalary;
    }
}
