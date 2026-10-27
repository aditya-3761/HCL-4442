package org.abes.hrmsdatabase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    private long emp_id;
    private String emp_name;

    private String email;
    private String stack;
    private String designation;
    private String password;
    public Employee(){
    }
    public Employee(long emp_id, String emp_name, String email, String stack, String designation, String password) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.email = email;
        this.stack = stack;
        this.designation = designation;
        this.password = password;
    }

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}