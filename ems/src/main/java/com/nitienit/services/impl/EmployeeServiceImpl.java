package com.nitienit.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitienit.entities.Employee;
import com.nitienit.forms.SingupForm;
import com.nitienit.helper.Constants;
import com.nitienit.repositries.EmployeeRespositry;
import com.nitienit.services.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRespositry employeeRespositry;

     @Override
    public void signup(SingupForm singupForm) {

        logger.info("HomeServiceImpl ::signup ");
        
        Employee employee= new Employee();
        employee.setId(generateId(employeeRespositry.findByIdJPA()));
        employee.setEmail(singupForm.getEmail());
       // employee.setPassword(passwordEncoder.encode(singupForm.getPassword()));
       employee.setPassword(singupForm.getPassword());
        employee.setContactNo(singupForm.getContactNo());
         // set the user role
        employee.setRoleList(List.of(Constants.ROLE_USER));
        employeeRespositry.save(employee);
    }

    // @Override
    // public Employee saveEmployee(Employee employee) {

    //     logger.info("employee======{}",employee.getEmail());
    //     logger.info("employee======{}",employee.getPassword());
    //    // logger.info("employee======{}",employee.getRole());

    //     employee.setId(generateId(employeeRespositry.findByIdJPA()));
        
    //     return employeeRespositry.save(employee);

       
    // }

    public String generateId(String id) {
        String[] str ;
		logger.info("id======{}",id);
        if(id==null || id==""){
            id="kma-001";
        }else{
            str = id.split("-");
	        String.valueOf(Integer.parseInt(str[1])+1);
	       
            id= "kma-00"+String.valueOf(Integer.parseInt(str[1])+1); 
        }
	    logger.info("new id ======{}",id);     
		return id;
	}

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        
        return employeeRespositry.findById(id);
    }

    @Override
    public void deleteEmployeeById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployeeById'");
    }

   
    

    @Override
    public Employee updateEmployee(Employee employee) {
        
       Employee dbEmployee= employeeRespositry.findById(employee.getId())
       .orElseThrow(() -> new RuntimeException("EMployee  not found in db"));

       dbEmployee.setAddress(employee.getAddress());
       dbEmployee.setBankAccNo(employee.getBankAccNo());
       dbEmployee.setContactNo(employee.getContactNo());
       dbEmployee.setFirstName(employee.getFirstName());
       dbEmployee.setLastName(employee.getLastName());

       employeeRespositry.save(dbEmployee);

       return dbEmployee;

    }

    @Override
    public Employee findByEmail(String email) {
       return employeeRespositry.findByEmail(email);
    }

}
