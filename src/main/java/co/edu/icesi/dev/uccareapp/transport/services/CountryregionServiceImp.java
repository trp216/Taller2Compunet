package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.repositories.CountryregionRepository;

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

	@Override
	public Iterable<Countryregion> findAll() {
		return repo.findAll();
	}

	@Transactional
	public void save(Countryregion cr) {
		//	
		repo.save(cr);
		//}
	}

	@Transactional
	public Countryregion edit(Countryregion countryregion) {
		Countryregion actual = null;

		if(countryregion.getCountryregionid() != null) {
			Optional<Countryregion> optional = repo.findById(countryregion.getCountryregionid());
			if(optional.isPresent()) {
				save(countryregion);
				actual = findById(countryregion.getCountryregionid()).get();
			}
		}

		return actual;
	}
}
