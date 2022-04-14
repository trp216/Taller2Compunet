package com.example.demo.services;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.FailedValidationsException;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

public interface StateprovinceService {
	
	public Stateprovince saveStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException;
	
	public Stateprovince editStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException;
		
		
	
}
