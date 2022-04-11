package com.sherlock.carpark.Service.iService;

import com.sherlock.carpark.DTO.RequestDTO.EmployeeRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.EmployeeResponseDTO;
import com.sherlock.carpark.Entity.Employee;
import com.sherlock.carpark.Entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface iEmployeeService {
    public abstract ResponseEntity<ResponseObject> findAllEmployee(int pageNo, String sortBy, int maxElementPerPage);
    public abstract ResponseEntity<ResponseObject> saveEmployee(EmployeeRequestDTO newEmployeeDTO);
    public abstract ResponseEntity<ResponseObject> viewEmployeeById(int employeeId);
    public abstract Employee findEmployeeById(int employeeId);
    public abstract Employee convertToEntity(EmployeeRequestDTO employeeRequestDTO);
    public abstract EmployeeResponseDTO convertToDTO (Employee employeeEntity);
}
