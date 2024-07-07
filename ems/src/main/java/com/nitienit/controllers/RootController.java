package com.nitienit.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nitienit.entities.Employee;
import com.nitienit.services.EmployeeService;


@ControllerAdvice
public class RootController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeService employeeService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        logger.info("Adding logged in user information to the model");       
        String email=authentication.getName();
        logger.info("User dashboard ============{}",authentication.getName());
        Employee employee=  employeeService.findByEmail(email);
        
        logger.info("getEmail==============={}",employee.getEmail());
        logger.info("id==============={}",employee.getId());
        logger.info("getanme==============={}", employee.getFirstName());

        model.addAttribute("loggedInEmployee", employee);
      
       

    }
}