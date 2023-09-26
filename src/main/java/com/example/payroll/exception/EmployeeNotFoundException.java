package com.example.payroll.exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
