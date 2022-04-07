package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Entity.Employee;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/CarPark/HRM")
public class HumanResourceManagement {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/viewAllEmployee")
    ResponseEntity<ResponseObject> viewAllEmployee() {
        return employeeService.findAllEmployee();
    }

    @PostMapping("/addEmployee")
    @ResponseBody
    ResponseEntity<ResponseObject> addEmployee(@RequestBody @Valid Employee newEmployee) {
        return employeeService.saveEmployee(newEmployee);
    }

    @GetMapping("/viewEmployee/{employeeId}")
    ResponseEntity<ResponseObject> viewEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
}
