package com.Corhuila.backend_security.Service;

/* #region Import */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Entity.FormModule;
import com.Corhuila.backend_security.IRepository.IFormModuleRepository;
import com.Corhuila.backend_security.IService.IFormModuleService;
import com.Corhuila.backend_security.Utils.GlobalConstants;

/**
 * This class provides the implementation for the IFormModuleService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class FormModuleService implements IFormModuleService {
	/**
	 * Injects an instance of IFormModuleRepository into this class.
	 */
	@Autowired
	public IFormModuleRepository repository;

	/**
	 * Returns all FormModule entities from the database.
	 *
	 * @return a List of all FormModule entities
	 */
	@Override
	public List<FormModule> all() {
		return repository.findAll();
	}

	/**
	 * Returns an Optional containing the FormModule entity with the specified id, if
	 * it exists in the database.
	 *
	 * @param id the id of the FormModule entity to retrieve
	 * @return an Optional containing the FormModule entity with the specified id, or
	 *         an empty Optional if it doesn't exist
	 */
	@Override
	public FormModule findById(Long id) throws Exception {
		Optional<FormModule> formModule = repository.findById(id);

		if (formModule.isEmpty())
			throw new Exception("Registro no encontrado");

		return formModule.get();
	}

	/**
	 * Saves the specified FormModule entity to the database.
	 *
	 * @param formModule the FormModule entity to save
	 * @return the saved FormModule entity
	 */
	@Override
	public FormModule save(FormModule formModule) {
		
		return repository.save(formModule);
	}

	/**
	 * Update specified register
	 *
	 * @param id       the id entity to Update
	 * @param formModule the FormModule entity to Update
	 * @return the Update FormModule entity
	 */
	@Override
	public void update(Long id, FormModule formModule) throws Exception {
		Optional<FormModule> formModuleSearch = repository.findById(id);

		if (formModuleSearch.isEmpty())
			throw new Exception("Registro no encontrado");

		FormModule formModuleSaved = formModuleSearch.get();

		BeanUtils.copyProperties(formModule, formModuleSaved, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

		this.repository.save(formModuleSaved);
	}

	/**
	 * Deletes the FormModule entity with the specified id from the database.
	 *
	 * @param id the id of the FormModule entity to delete
	 */
	@Override
	public void delete(Long id) throws Exception {
		Optional<FormModule> formModuleOptional = repository.findById(id);

		if (formModuleOptional.isEmpty())
			throw new Exception("Registro no encontrado");

		FormModule formModuleSave = formModuleOptional.get();
		
		this.repository.save(formModuleSave);
	}
}
