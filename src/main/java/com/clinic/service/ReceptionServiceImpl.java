package com.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.Receptionist;
import com.clinic.entity.repository.ReceptionRepository;
@Service
public class ReceptionServiceImpl implements ReceptionService{
	@Autowired
	ReceptionRepository receptionRepo;
	@Override
	public Receptionist createReception(Receptionist reception) {
		// TODO Auto-generated method stub
		return receptionRepo.save(reception);
	}

	@Override
	public boolean checkResEmailid(String emailid) {
		// TODO Auto-generated method stub
		return receptionRepo.existsByEmailid(emailid);
	}

}
