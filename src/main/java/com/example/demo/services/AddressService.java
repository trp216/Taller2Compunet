package com.example.demo.services;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.FailedValidationsException;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;

public interface AddressService {
	
public Address saveAddress(int i, Address address) throws FailedValidationsException, ElementNotFoundException;
	
	public Address editAddress(int i, Address address) throws FailedValidationsException, ElementNotFoundException;


}
