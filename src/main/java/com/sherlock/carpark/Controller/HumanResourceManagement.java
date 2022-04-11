package com.sherlock.carpark.Controller;

import com.sherlock.carpark.DTO.RequestDTO.EmployeeRequestDTO;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Service.iService.iEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/CarPark/HRM")
public class HumanResourceManagement {

    private final iEmployeeService employeeService;

    @Autowired
    public HumanResourceManagement(iEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/viewAllEmployee")
    ResponseEntity<ResponseObject> viewAllEmployee(@RequestParam Optional<Integer> pageNo,
                                                   @RequestParam Optional<String> sortBy,
                                                   @RequestParam Optional<Integer> maxElementPerPage) {
        return employeeService.findAllEmployee(pageNo.orElse(0), sortBy.orElse("employeeId"), maxElementPerPage.orElse(1));
    }

    @PostMapping("/addEmployee")
    @ResponseBody
    ResponseEntity<ResponseObject> addEmployee( @Valid @RequestBody EmployeeRequestDTO newEmployeeDTO) {
        return employeeService.saveEmployee(newEmployeeDTO);
    }

    @GetMapping("/viewEmployee/{employeeId}")
    ResponseEntity<ResponseObject> viewEmployeeById(@PathVariable int employeeId) {
        return employeeService.viewEmployeeById(employeeId);
    }
}
