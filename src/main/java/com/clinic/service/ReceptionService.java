package com.clinic.service;

import com.clinic.entity.Receptionist;

public interface ReceptionService {
	public Receptionist createReception(Receptionist reception);
	public boolean checkResEmailid(String emailid);
}
