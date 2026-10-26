package org.abes.hrmsdatabase.service;

import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String createNewEmployee(Employee emp) {
        Employee emp1 = new Employee(
                2001,
                "Ram",
                "Java FSD",
                "Developer"
        );
        employeeRepository.save(emp1);
        return "Employee is added successfully";
    }

    public Employee getEmployeeById(long empid) {
        return employeeRepository.findById(empid).orElse(null);
    }

    public String deleteById(long empid) {
        if (employeeRepository.existsById(empid)) {
            employeeRepository.deleteById(empid);
            return "Employee deleted successfully";
        }
        return "Employee not found with ID: " + empid;
    }
}