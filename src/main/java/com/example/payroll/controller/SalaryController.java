package com.example.payroll.controller;

import com.example.payroll.exception.EmployeeNotFoundException;
import com.example.payroll.service.SalaryCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryCalculatorService salaryCalculatorService;

    @GetMapping("/totalFixedPay/{employeeId}")
    public double getTotalFixedPayByEmployeeId(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return salaryCalculatorService.calculateTotalFixedPayByEmployeeId(employeeId);
    }

    @GetMapping("/totalTargetCash/{employeeId}")
    public double getTotalTargetCashByEmployeeId(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return salaryCalculatorService.calculateTotalTargetCashByEmployeeId(employeeId);
    }

    @GetMapping("/totalFixedPayByName/{employeeName}")
    public double getTotalFixedPayByEmployeeName(@PathVariable String employeeName) throws EmployeeNotFoundException {
        return salaryCalculatorService.calculateTotalFixedPayByEmployeeName(employeeName);
    }

    @GetMapping("/totalTargetCashByName/{employeeName}")
    public double getTotalTargetCashByEmployeeName(@PathVariable String employeeName) throws EmployeeNotFoundException {
        return salaryCalculatorService.calculateTotalTargetCashByEmployeeName(employeeName);
    }

    @GetMapping("/totalCostOfBenefits/{employeeId}")
    public double getTotalCostOfBenefitsByEmployeeId(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return salaryCalculatorService.calculateTotalCostOfBenefitsByEmployeeId(employeeId);
    }

    @GetMapping("/benefitsPercentage/{employeeId}")
    public double getBenefitsPercentage(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return salaryCalculatorService.calculateBenefitsPercentage(employeeId);
    }
}

