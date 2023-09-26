package com.example.payroll.controller;

import com.example.payroll.EmployeeRepository.EmployeeRepository;
import com.example.payroll.entity.Employee;
import com.example.payroll.entity.SalaryComponent;
import com.example.payroll.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    // Update an employee's details
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setSalaryComponent(updatedEmployee.getSalaryComponent());
                    employee.setFreeMeals(updatedEmployee.getFreeMeals());
                    employee.setFreeTransport(updatedEmployee.getFreeTransport());
                    employee.setLifeEvents(updatedEmployee.getLifeEvents());
                    employee.setOwnSAP(updatedEmployee.getOwnSAP());
                    employee.setWellnessCover(updatedEmployee.getWellnessCover());
                    employee.setHigherEducationPolicy(updatedEmployee.getHigherEducationPolicy());
                    employee.setCostOfBenefits(updatedEmployee.getCostOfBenefits());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    // Get salary details of an employee
    @GetMapping("/{id}/salaryDetails")
    public SalaryComponent getSalaryDetailsByEmployeeId(@PathVariable Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return employee.getSalaryComponent();
    }
}
