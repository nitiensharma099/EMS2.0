package com.nitienit.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nitienit.entities.Employee;

public interface EmployeeRespositry extends JpaRepository<Employee, String>{

    @Query("SELECT MAX(e.id) FROM Employee e")
    String findByIdJPA();

    public Employee findByEmail(String  email);

}
