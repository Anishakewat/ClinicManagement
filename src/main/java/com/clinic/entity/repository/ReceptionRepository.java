package com.clinic.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinic.entity.Receptionist;
@Repository
public interface ReceptionRepository extends JpaRepository<Receptionist, Long>{
	public boolean existsByEmailid(String emailid);
	Receptionist findByEmailid(String emailid);
}
