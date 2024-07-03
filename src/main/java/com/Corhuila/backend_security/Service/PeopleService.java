package com.Corhuila.backend_security.Service;

/* #region Import */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Corhuila.backend_security.Dto.IPeopleDto;
import com.Corhuila.backend_security.Entity.People;
import com.Corhuila.backend_security.IRepository.IPeopleRepository;
import com.Corhuila.backend_security.IService.IPeopleService;
import com.Corhuila.backend_security.Utils.GlobalConstants;
/* #endregion */

/**
 * This class provides the implementation for the IPeopleService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class PeopleService implements IPeopleService {
	/**
	 * Injects an instance of IPeopleRepository into this class.
	 */
	@Autowired
	public IPeopleRepository repository;
	
	/**
	 * Datatable data
	 * 
	 * @param pageable
	 * @param search
	 * @return datatable data
	 */
	@Override
	public Page<IPeopleDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

	/**
	 * Returns all People entities from the database.
	 *
	 * @return a List of all People entities
	 */
	@Override
	public List<People> all() {
		return repository.findByStateTrue();
	}

	/**
	 * Returns an Optional containing the People entity with the specified id, if
	 * it exists in the database.
	 *
	 * @param id the id of the People entity to retrieve
	 * @return an Optional containing the People entity with the specified id, or
	 *         an empty Optional if it doesn't exist
	 */
	@Override
	public People findById(Long id) throws Exception {
		Optional<People> people = repository.findById(id);

		if (people.isEmpty())
			throw new Exception("Registro no encontrado");

		return people.get();
	}

	/**
	 * Saves the specified People entity to the database.
	 *
	 * @param people the People entity to save
	 * @return the saved People entity
	 */
	@Transactional
	@Override
	public People save(People people) throws Exception {
		people.setCreatedAt(LocalDateTime.now());
		People peopleSave = repository.save(people);	
		return peopleSave;
	}

	/**
	 * Update specified register
	 *
	 * @param id       the id entity to Update
	 * @param people the People entity to Update
	 * @return the Update People entity
	 */
	@Override
	public void update(Long id, People people) throws Exception {
		Optional<People> peopleSearch = repository.findById(id);

		if (peopleSearch.isEmpty())
			throw new Exception("Registro no encontrado");

		People peopleSaved = peopleSearch.get();

		BeanUtils.copyProperties(people, peopleSaved, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

		peopleSaved.setUpdatedAt(LocalDateTime.now());

		this.repository.save(peopleSaved);
	}

	/**
	 * Deletes the People entity with the specified id from the database.
	 *
	 * @param id the id of the People entity to delete
	 */
	@Override
	public void delete(Long id) throws Exception {
		Optional<People> peopleOptional = repository.findById(id);

		if (peopleOptional.isEmpty())
			throw new Exception("Registro no encontrado");

		People peopleSave = peopleOptional.get();
		peopleSave.setDeletedAt(LocalDateTime.now());
		this.repository.save(peopleSave);
	}

	@Override
	public People getPeopleByDocumentNumber(String documentNumber) throws Exception {
		Optional<People> people = repository.getPeopleByDocumentNumber(documentNumber);

		if (people.isEmpty())
			throw new Exception("Registro no encontrado");

		return people.get();
	}
}
