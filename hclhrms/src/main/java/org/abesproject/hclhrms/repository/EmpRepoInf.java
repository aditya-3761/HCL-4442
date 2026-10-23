package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;

import org.abesproject.hclhrms.repository.EmployeeRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpRepoInf implements EmployeeRepo {
    List<Employee> emplist= new ArrayList<>();
    @Override
    public String addEmp(Employee emp) {
        emplist.add(emp);
        return "Employee added successfully!";
    }

    @Override
    public String addEmpBulk(List<Employee> employeeList) {
        if (employeeList != null) {
            emplist.addAll(employeeList);
        }
        return "Bulk employees added successfully!";
    }


    @Override
    public List<Employee> getEmp() {
        return emplist;
    }

    @Override
    public Employee getEmpById(long empid) {
        return emplist.stream().filter(e->e.getEmp_id()==empid).findFirst().orElse(null);

    }

    @Override
    public String deleteById(long empid) {
        return "";
    }

    @Override
    public String updateById(long empid, Employee updatedEmp) {
        return "";
    }
}