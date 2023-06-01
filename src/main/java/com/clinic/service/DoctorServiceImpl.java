package com.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.Doctor;
import com.clinic.entity.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	private DoctorRepository doctorRepo;
	@Override
	public Doctor createDoctor(Doctor doctor) {
		return doctorRepo.save(doctor);
	}
	@Override
	public boolean checkDocEmail(String email) {
		
		return doctorRepo.existsByEmail(email);
	}
		
}
