package com.nitienit.services;

import java.util.Optional;

import com.nitienit.entities.Employee;
import com.nitienit.forms.SingupForm;

public interface EmployeeService {

     void signup(SingupForm singupForm);

   // Employee saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(String id);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(String id);

    Employee findByEmail(String  email);

}
