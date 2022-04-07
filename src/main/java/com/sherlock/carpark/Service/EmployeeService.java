package com.sherlock.carpark.Service;

import com.sherlock.carpark.Entity.Employee;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<ResponseObject> findAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query All Employees successfully", employeeList)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not Found", "Cannot find any Employees", "")
            );
        }
    }

    public ResponseEntity<ResponseObject> saveEmployee(Employee newEmployee) {
        employeeRepository.save(newEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add employee to Database Successfully", newEmployee)
        );
    }

    public ResponseEntity<ResponseObject> findEmployeeById(int employeeId) {
        Optional<Employee> foundEmployee = employeeRepository.findById(employeeId);
        if (foundEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Query Employee by ID successfully", foundEmployee)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Not Found", "Cannot find Employee with Id = "+employeeId, "")
            );
        }
    }
}
