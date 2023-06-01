package com.clinic.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	public boolean existsByEmail(String email);
	Patient findByEmail(String email);
}
