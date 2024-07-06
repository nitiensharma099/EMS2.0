package com.nitienit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nitienit.entities.Employee;
import com.nitienit.forms.SingupForm;
import com.nitienit.helper.Message;
import com.nitienit.helper.MessageType;
import com.nitienit.services.EmployeeService;
import com.nitienit.services.impl.EmployeeServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class homeController {

    private Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeService employeeService;   
   
    @GetMapping({"","/","/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/service")
    public String service(){
        return "service";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String singup(Model model){

        SingupForm singupForm=new SingupForm();
        model.addAttribute("singupForm", singupForm);
        return "signup";
    }
    @PostMapping("/do-singup")
    public String singupProcess(@Valid @ModelAttribute SingupForm singupForm , BindingResult result,HttpSession session) {
        
        logger.info("singupForm.getEmail()======{}",singupForm.getEmail());
        if (!singupForm.getPassword().equalsIgnoreCase(singupForm.getConfirmPassword())) {
			logger.info("[EmployeeController] :: Password didnt match");
			result.addError(
					new FieldError("singupForm", "confirmPassword", "Password and Confirm Password do not match"));
		}
        Employee dbEmployee = employeeService.findByEmail(singupForm.getEmail());
		if (dbEmployee != null) {
			logger.info("[EmployeeController] :: Email Id already there{}",singupForm.getEmail() );
			result.addError(new FieldError("singupForm", "email", "Email is already registered"));
		}
		if (result.hasErrors()) {
			logger.info("[EmployeeController] ::hasErrors " );
			return "signup";
		}


        Employee employee= new Employee();
        employee.setEmail(singupForm.getEmail());
        employee.setPassword(singupForm.getPassword());
        employee.setContactNo(singupForm.getContactNo());
        employee.setRole("USER");
        employeeService.saveEmployee(employee);

        Message message = Message.builder().content("Singup Done! wait for ADMIN for verification").type(MessageType.green).build();

        session.setAttribute("message", message);

        
        return "success";
    }
    
}
