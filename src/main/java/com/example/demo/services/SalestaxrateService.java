package com.example.demo.services;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.FailedValidationsException;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

public interface SalestaxrateService {
	
	public Salestaxrate saveSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException;
	
	public Salestaxrate editSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException;

}
