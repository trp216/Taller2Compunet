package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.FailedValidationsException;
import com.example.demo.repositories.CountryregionRepository;
import com.example.demo.repositories.SalesTerritoryRepository;
import com.example.demo.repositories.StateprovinceRepository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Service
public class StateprovinceServiceImp implements StateprovinceService{
	
	private StateprovinceRepository repo;
	
	private SalesTerritoryRepository stRepo;
	private CountryregionRepository crRepo;
	
	
	@Autowired
	public StateprovinceServiceImp(StateprovinceRepository repo, SalesTerritoryRepository stRepo, CountryregionRepository crRepo) {
		this.repo = repo;
		this.stRepo = stRepo;
		this.crRepo = crRepo;
	}



	@Override
	public Stateprovince saveStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException {
		Stateprovince result = null;
		
		
		
		if(stateProvince.getStateprovincecode()!=null && stateProvince.getStateprovincecode().length()!=5 ) {
			throw new FailedValidationsException("El codigo del estado-provincia debe tener 5 digitos");
		}
		if(stateProvince.getIsonlystateprovinceflag()!=null && !stateProvince.getIsonlystateprovinceflag().equals("Y/N")){
			throw new FailedValidationsException("El flag de estado debe ser Y/N");
		}
		if( stateProvince.getName()!=null && stateProvince.getName().length()<5) {
			throw new FailedValidationsException("El nombre del estado-provincia debe tener al menos 5 caracteres");
		}
		else {
			Optional<Salesterritory> opt1 = this.stRepo.findById(salesterritoryid);
			if(opt1.isPresent()) {
				Optional<Countryregion> opt2 = this.crRepo.findById(countryregionid);
				if(opt2.isPresent()) {
					stateProvince.setCountryregion(opt2.get());
					result = this.repo.save(stateProvince);
				}
				else {
					throw new ElementNotFoundException("El pais-region no existe");
				}
			
			}
			else {
				throw new ElementNotFoundException("El territorio de ventas no existe");
			}
			
		}
		
		return result;
	}



	@Override
	@Transactional
	public Stateprovince editStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException {
		Stateprovince result = null;
		if(stateProvince.getStateprovinceid()!=null) {
			Optional<Stateprovince> old = repo.findById(stateProvince.getStateprovinceid());
			if(old.isPresent()) {
				result = saveStateprovince(stateProvince,salesterritoryid,countryregionid);
			}
		}
		
		
		return result;
	}

	public Optional<Stateprovince> findById(Integer id) {
		return repo.findById(id);
	}

}
