package com.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.Patient;
import com.clinic.entity.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	@Autowired
	private PatientRepository patientRepo;
	@Override
	public Patient createPatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepo.save(patient);
	}
	@Override
	public boolean checkPatEmail(String email) {
		// TODO Auto-generated method stub
		return patientRepo.existsByEmail(email);
	}

}
