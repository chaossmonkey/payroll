package com.example.payroll.service;

import com.example.payroll.EmployeeRepository.EmployeeRepository;
import com.example.payroll.entity.Employee;
import com.example.payroll.entity.SalaryComponent;
import com.example.payroll.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryCalculatorService {

   @Autowired
    private EmployeeRepository employeeRepository;

    public double calculateTotalFixedPayByEmployeeId(Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee != null) {
            SalaryComponent salaryComponent = employee.getSalaryComponent();
            return salaryComponent.getBasicSalary() +
                    salaryComponent.getFlexibleComponents() +
                    salaryComponent.getBenefitsAllowance() +
                    salaryComponent.getProvidentFund() +
                    salaryComponent.getGratuity() +
                    salaryComponent.getSuperannuation();
        } else {
            throw new EmployeeNotFoundException("employee Not Found");
        }
    }

    public double calculateTotalTargetCashByEmployeeId(Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee != null) {
            SalaryComponent salaryComponent = employee.getSalaryComponent();
            double variablePay = (salaryComponent.getTotalTargetCash() * salaryComponent.getVariablePay()) / 100;
            return calculateTotalFixedPayByEmployeeId(employeeId) + variablePay;
        } else {
            throw new EmployeeNotFoundException("employee Not Found");
        }
    }

    public double calculateTotalFixedPayByEmployeeName(String employeeName) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findByName(employeeName);

        if (employee != null) {
            SalaryComponent salaryComponent = employee.getSalaryComponent();
            return salaryComponent.getBasicSalary() +
                    salaryComponent.getFlexibleComponents() +
                    salaryComponent.getBenefitsAllowance() +
                    salaryComponent.getProvidentFund() +
                    salaryComponent.getGratuity() +
                    salaryComponent.getSuperannuation();
        } else {
            throw new EmployeeNotFoundException("employee Not Found");
        }
    }

    public double calculateTotalTargetCashByEmployeeName(String employeeName) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findByName(employeeName);

        if (employee != null) {
            SalaryComponent salaryComponent = employee.getSalaryComponent();
            double totalFixedPay = salaryComponent.getBasicSalary() +
                    salaryComponent.getFlexibleComponents() +
                    salaryComponent.getBenefitsAllowance() +
                    salaryComponent.getProvidentFund() +
                    salaryComponent.getGratuity() +
                    salaryComponent.getSuperannuation();

            double variablePay = (salaryComponent.getTotalTargetCash() * salaryComponent.getVariablePay()) / 100;
            return totalFixedPay + variablePay;
        } else {
            throw new EmployeeNotFoundException("employee Not Found ");
        }
    }


    public double calculateTotalCostOfBenefitsByEmployeeId(Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee != null) {
            SalaryComponent salaryComponent = employee.getSalaryComponent();
            return employee.getFreeMeals() +
                    employee.getFreeTransport() +
                    employee.getLifeEvents() +
                    employee.getOwnSAP() +
                    employee.getWellnessCover() +
                    employee.getHigherEducationPolicy() +
                    employee.getCostOfBenefits();
        } else {
            throw new EmployeeNotFoundException("employee Not Found");
        }
    }
    // Calculate the percentage of benefits cost in relation to total fixed pay
    public double calculateBenefitsPercentage(Long employeeId) throws EmployeeNotFoundException {
        double totalFixedPay = calculateTotalFixedPayByEmployeeId(employeeId);
        double totalCostOfBenefits = calculateTotalCostOfBenefitsByEmployeeId(employeeId);

        if (totalFixedPay != 0) {
            return (totalCostOfBenefits / totalFixedPay) * 100;
        } else {
            return 0.0;
        }
    }

}
