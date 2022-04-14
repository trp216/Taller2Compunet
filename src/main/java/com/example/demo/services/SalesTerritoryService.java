package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.SalesTerritoryRepository;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Service
public class SalesTerritoryService {
	
	private SalesTerritoryRepository repo;

	@Autowired
	public SalesTerritoryService(SalesTerritoryRepository repo) {
		super();
		this.repo = repo;
	}
	
	public Salesterritory save(Salesterritory s) {
		return repo.save(s);
	}
	
	public Optional<Salesterritory> findById(Integer id) {
		return repo.findById(id);
	}

}
