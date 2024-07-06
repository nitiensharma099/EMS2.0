package com.nitienit.entities;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="kma_employee")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee {

	@Id
	private String id;
    @Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;
	private String role;

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
}
