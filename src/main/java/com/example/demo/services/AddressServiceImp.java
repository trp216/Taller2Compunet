package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.FailedValidationsException;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.StateprovinceRepository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Service
public class AddressServiceImp implements AddressService{
	
	private AddressRepository repo;
	
	private StateprovinceRepository spRepo;
	
	@Autowired
	public AddressServiceImp(AddressRepository repo, StateprovinceRepository spRepo) {
		super();
		this.repo = repo;
		this.spRepo = spRepo;
	}

	@Override
	public Address saveAddress(int stateProvinceId, Address address) throws FailedValidationsException, ElementNotFoundException {
		Address result = null;
		
		if(address.getAddressline1()==null || address.getAddressline1().isEmpty() || address.getAddressline1().isBlank()) {
			throw new FailedValidationsException("La linea 1 de direccion no debe estar vacia");
		}
		else if(address.getCity().length()<3) {
			throw new FailedValidationsException("El nombre de la ciudad debe tener al menos 3 caracteres");
		}
		else if(address.getPostalcode().length()!=6) {
			throw new FailedValidationsException("El codigo postal debe tener seis digitos");
		}
		else {
			Optional<Stateprovince> opt1 = this.spRepo.findById(stateProvinceId);
			if(opt1.isPresent()) {
				address.setStateprovince(opt1.get());
				result = this.repo.save(address);
			}
			else {
				throw new ElementNotFoundException("El estado-provincia no existe");
			}
		}
		
		return result;
	}

	@Override
	@Transactional
	public Address editAddress(int stateProvinceId, Address address) throws FailedValidationsException, ElementNotFoundException{
		Address result = null;
		
		if(address.getAddressid()!=null) {
			Optional<Address> old = repo.findById(address.getAddressid());
			if(old.isPresent()) {
				result = saveAddress(stateProvinceId, address);
			}
		}
		
		return result;
	}
	
	public Optional<Address> findById(Integer id) {
		return repo.findById(id);
	}

}
