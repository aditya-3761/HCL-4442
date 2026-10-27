package org.abes.hrmsdatabase.controller;

import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/v1/user")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String welcome(){
        return "<h1> Welcome to HRMS Database </h1>";
    }
    @PostMapping("/addemp")
    public String addNewEmployee(@RequestBody Employee employee){
        System.out.println(employee.getEmail());
        return employeeService.createNewEmployee(employee);
    }

    @GetMapping("/findById/{empid}")
    public String getById(@PathVariable long empid){
        return employeeService.getById(empid);
    }
    @GetMapping("/deleteByid/{empid}")
    public String deleteById(long id){
        return employeeService.deleteById(id);
    }
    @PutMapping("/updateEmployee/{empid}")
    public String updateEmployee(
            @PathVariable long empid,
            @RequestBody Employee emp) {

        return employeeService.updateEmployee(empid, emp);
    }
}