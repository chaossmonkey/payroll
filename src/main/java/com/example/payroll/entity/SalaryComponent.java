package com.example.payroll.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalaryComponent {
    private double basicSalary;
    private double flexibleComponents;
    private double benefitsAllowance;
    private double providentFund;
    private double gratuity;
    private double superannuation;
    private double variablePay;
    private double totalTargetCash;

}
