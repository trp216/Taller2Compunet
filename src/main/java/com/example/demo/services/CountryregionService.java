package com.example.demo.services;

import com.example.demo.exception.FailedValidationsException;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

public interface CountryregionService {
	
	public Countryregion saveCountryRegion(Countryregion cr) throws FailedValidationsException;
	
	public Countryregion editCountryRegion(Countryregion cr) throws FailedValidationsException;
	
}
