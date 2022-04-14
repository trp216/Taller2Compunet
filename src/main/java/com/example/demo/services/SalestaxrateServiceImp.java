package com.example.demo.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.FailedValidationsException;
import com.example.demo.repositories.SalestaxrateRepository;
import com.example.demo.repositories.StateprovinceRepository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@Service
public class SalestaxrateServiceImp implements SalestaxrateService{

	private SalestaxrateRepository repo;
	
	private StateprovinceRepository spRepo;
	
	@Autowired
	public SalestaxrateServiceImp(SalestaxrateRepository repo, StateprovinceRepository spRepo) {
		super();
		this.repo = repo;
		this.spRepo = spRepo;
	}

	@Override
	public Salestaxrate saveSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException {
		Salestaxrate result  = null;
		
		if(str.getName()==null || str.getName().isBlank() || str.getName().length()<5) {
			throw new FailedValidationsException("El nombre debe tener al menos 5 caracteres");
		}
		else if(str.getTaxrate().signum()<0) {
			throw new FailedValidationsException("La tasa no debe ser negativa");
			
		}
		else {
			Optional<Stateprovince> opt1 = this.spRepo.findById(stateprovinceid);
			if(opt1.isPresent()) {
				str.setStateprovinceid(stateprovinceid);
				result = this.repo.save(str);
			}else {
				throw new ElementNotFoundException("El estado provincia no existe");
			}
		}
		
		return result;
	}

	@Override
	@Transactional
	public Salestaxrate editSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException{
		Salestaxrate result  = null;
		if(str.getSalestaxrateid()!=null) {
			Optional<Salestaxrate> old = repo.findById(str.getSalestaxrateid());
			if(old.isPresent()) {
				result = saveSalestaxrate(str, stateprovinceid);
			}
		}
		return result;
	}
	
	public Optional<Salestaxrate> findById(Integer id) {
		return repo.findById(id);
	}

	

}
