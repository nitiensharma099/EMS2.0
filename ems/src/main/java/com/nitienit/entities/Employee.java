package com.nitienit.entities;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="kma_employee")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee implements UserDetails {

	@Id
	private String id;
    @Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Getter(AccessLevel.NONE)
	private String password;
	

	@Column(insertable = false)
	private String firstName;
	@Column(insertable = false)
	private String lastName;	
	@Column(insertable = false)
	private String address;
	@Column(insertable = false)
	private String contactNo;
	@Column(insertable = false)
	private String bankAccNo;
    @Column(insertable = false)
	private String profilePic;
	
	
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean contactVerified=false;

	@Enumerated(value = EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerEmployeeId;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime lastModifiedData;

	@ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 // list of roles[USER,ADMIN]
        // Collection of SimpGrantedAuthority[roles{ADMIN,USER}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
    
	
	
}
