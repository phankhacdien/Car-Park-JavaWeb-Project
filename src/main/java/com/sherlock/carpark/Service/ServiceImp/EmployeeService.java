package com.sherlock.carpark.Service.ServiceImp;

import com.sherlock.carpark.DTO.RequestDTO.EmployeeRequestDTO;
import com.sherlock.carpark.DTO.ResponseDTO.EmployeeResponseDTO;
import com.sherlock.carpark.Entity.Employee;
import com.sherlock.carpark.Entity.ResponseObject;
import com.sherlock.carpark.Exception.NotFoundException;
import com.sherlock.carpark.Repository.EmployeeRepository;
import com.sherlock.carpark.Service.iService.iEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements iEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObject> findAllEmployee(int pageNo, String sortBy, int maxElementPerPage) {
        List<EmployeeResponseDTO> employeeList = employeeRepository
                .findAll(PageRequest.of(pageNo, maxElementPerPage, Sort.Direction.ASC, sortBy)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

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

    @Override
    public ResponseEntity<ResponseObject> saveEmployee(EmployeeRequestDTO newEmployeeDTO) {
        Employee newEmployee = convertToEntity(newEmployeeDTO);
        employeeRepository.save(newEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Add employee to Database Successfully", newEmployee)
        );
    }

    public ResponseEntity<ResponseObject> viewEmployeeById(int employeeId) {
        Employee foundEmployee = findEmployeeById(employeeId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Query Employee by Id Successfully", convertToDTO(foundEmployee))
        );
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new NotFoundException("Cannot find Employee with Id = "+employeeId)
        );
    }

    @Override
    public Employee convertToEntity(EmployeeRequestDTO employeeRequestDTO) {
        return mapper.map(employeeRequestDTO, Employee.class);
    }

    @Override
    public EmployeeResponseDTO convertToDTO (Employee employeeEntity) {
        return mapper.map(employeeEntity, EmployeeResponseDTO.class);
    }
}

