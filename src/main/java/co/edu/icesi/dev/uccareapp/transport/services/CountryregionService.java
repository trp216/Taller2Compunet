package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

public interface CountryregionService {
	
	public Countryregion saveCountryRegion(Countryregion cr) throws FailedValidationsException;
	
	public Countryregion editCountryRegion(Countryregion cr) throws FailedValidationsException;
	
}