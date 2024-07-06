package com.nitienit.services;

import java.util.Optional;

import com.nitienit.entities.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(String id);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(String id);

    Employee findByEmail(String  email);

}
