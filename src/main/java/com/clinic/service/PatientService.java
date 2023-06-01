package com.clinic.service;

import com.clinic.entity.Patient;

public interface PatientService {
	public Patient createPatient(Patient patient);
	public boolean checkPatEmail(String email);
}
