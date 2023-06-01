package com.clinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class Patient {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	  
	    private String username;
	    
	   
	    private String contact;
	    
	   
	    private String email;
	    
	   
	    private String medicalHistory;
	    
	    
	    private String insurance;

	    
	    private String password;
}
