package com.nitienit.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nitienit.repositries.EmployeeRespositry;




@Service
public class SecurityCustomUserDetailServiceImpl implements UserDetailsService {

     @Autowired
    private EmployeeRespositry employeeRespositry;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return employeeRespositry.findByEmail(email);
    }

}
