package org.abes.hrmsdatabase.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String msg() {
        return "Welcome to Spring Boot HRMS";
    }

    @GetMapping("/addemp")
    public String addEmployee(
            @RequestParam long emp_id,
            @RequestParam String emp_name,
            @RequestParam String stack,
            @RequestParam String designation) {
        Employee emp = new Employee(
                emp_id, emp_name, stack, designation
        );
        return employeeService.createNewEmployee(emp);
    }

    @GetMapping("/getempbyid/{emp_id}")
    public Employee getEmpById(@PathVariable long emp_id) {
        return employeeService.getEmployeeById(emp_id);
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@RequestParam long empid, @PathVariable String id) {
        return employeeService.deleteById(empid);
    }
}