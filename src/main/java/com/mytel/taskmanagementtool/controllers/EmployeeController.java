package com.mytel.taskmanagementtool.controllers;

import com.mytel.taskmanagementtool.models.City;
import com.mytel.taskmanagementtool.models.Employee;
import com.mytel.taskmanagementtool.repositories.CityRepository;
import com.mytel.taskmanagementtool.repositories.EmployeeRepository;
import com.mytel.taskmanagementtool.services.EmployeeService;
import com.mytel.taskmanagementtool.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //list
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/employees/UserName/{userName}")
    public ResponseEntity<List<Employee>> getEmployeeByFirstName(@PathVariable(value = "userName") String userName)
            throws ResourceNotFoundException {
        List<Employee> employeeList = employeeRepository.findByUserName(userName);
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/employees/city/{city}")
    public ResponseEntity<List<Employee>> getEmployeeByCity(@PathVariable(value = "city") String city)
        throws ResourceNotFoundException{
        List<Employee> employeeList = employeeRepository.findByCity(city);
        return  ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/employees/city/population/{population}")
    public ResponseEntity<List<Employee>> getEmployeeByCityWithPopulation(@PathVariable(value = "population") Integer population)
        throws ResourceNotFoundException{
        List<Employee> employeeList = employeeRepository.findByCityWithPopulation(population);
        return  ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/employees/city/square/{square}")
    public ResponseEntity<List<Employee>> getEmployeeByCityWithSquare(@PathVariable(value = "square") Double square)
            throws ResourceNotFoundException{
        List<Employee> employeeList = employeeRepository.findByCityWithSquare(square);
        return  ResponseEntity.ok().body(employeeList);
    }


    //create
    @PostMapping("/employees")
    public Employee createEmployee(@Validated @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //update
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Validated @RequestBody Employee employeeDetail) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setName(employeeDetail.getName());
        employee.setDepartment(employeeDetail.getDepartment());
        employee.setPhoneNumber(employeeDetail.getPhoneNumber());
        employee.setPosition(employeeDetail.getPosition());
        employee.setVmyCode(employeeDetail.getVmyCode());
        employee.setCity(employeeDetail.getCity());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //delete
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
