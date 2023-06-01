package com.clinic.service;

import com.clinic.entity.Doctor;

public interface DoctorService {
	public Doctor createDoctor(Doctor doctor);
	public boolean checkDocEmail(String email);
}