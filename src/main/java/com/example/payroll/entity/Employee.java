package com.example.payroll.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private SalaryComponent salaryComponent;

    private double freeMeals;
    private double freeTransport;
    private double lifeEvents;
    private double ownSAP;
    private double wellnessCover;
    private double higherEducationPolicy;
    private double costOfBenefits;

}
