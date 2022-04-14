package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.FailedValidationsException;
import com.example.demo.repositories.CountryregionRepository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Service
public class CountryregionServiceImp implements CountryregionService{

	private CountryregionRepository repo;

	@Autowired
	public CountryregionServiceImp(CountryregionRepository repo) {
		this.repo = repo;
	}

	@Override
	public Countryregion saveCountryRegion(Countryregion cr) throws FailedValidationsException {
		Countryregion result = null;

		if(cr.getCountryregioncode()==null || cr.getCountryregioncode().length()<1 || cr.getCountryregioncode().length()>4) {
			throw new FailedValidationsException("El código debe tener entre 1 y 4 caracteres");
		}		
		else if(cr.getName()==null || cr.getName().length()<5) {
			throw new FailedValidationsException("El nombre debe tener al menos cinco caracteres");
		}
		else {
			result = this.repo.save(cr);
		}

		return result;
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Countryregion editCountryRegion(Countryregion cr) throws FailedValidationsException {
		Countryregion result = null;

		if(cr.getCountryregionid()!=null) {
			Optional<Countryregion> old = repo.findById(cr.getCountryregionid());
			if(old.isPresent()) {
				result = saveCountryRegion(cr);
				
			}
		}

		return result;
	}

	public Optional<Countryregion> findById(Integer id) {
		return repo.findById(id);
	}

}
