package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Repository
public interface StateprovinceRepository extends JpaRepository<Stateprovince, Integer>{

}