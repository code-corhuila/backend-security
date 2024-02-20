package com.corhuila.servicesecurity.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corhuila.servicecorhuila.Dto.IModuleDto;
import com.corhuila.servicecorhuila.Dto.ModuleDto;
import com.corhuila.servicesecurity.Entity.Form;
import com.corhuila.servicesecurity.Entity.FormModule;
import com.corhuila.servicesecurity.Entity.Module;
import com.corhuila.servicesecurity.IRepository.IFormModuleRepository;
import com.corhuila.servicesecurity.IRepository.IFormRepository;
import com.corhuila.servicesecurity.IRepository.IModuleRepository;
import com.corhuila.servicesecurity.IService.IModuleService;
import com.corhuila.servicesecurity.Utils.GlobalConstants;

/**
 * This class provides the implementation for the IModuleService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class ModuleService implements IModuleService {
	/**
	 * Injects an instance of IModuleRepository into this class.
	 */
	@Autowired
	private IModuleRepository repository;
	
	@Autowired
	private IFormModuleRepository formModuleRepository;
	
	@Autowired
	private IFormRepository formRepository;

	/**
	 * Datatable data
	 * 
	 * @param pageable
	 * @param search
	 * @return datatable data
	 */
	@Override
	public Page<IModuleDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

	/**
	 * Returns all Module entities from the database.
	 *
	 * @return a List of all Module entities
	 */
	@Override
	public List<Module> all() {
		return repository.findByStateTrue();
	}

	/**
	 * Returns an Optional containing the Module entity with the specified id, if
	 * it exists in the database.
	 *
	 * @param id the id of the Module entity to retrieve
	 * @return an Optional containing the Module entity with the specified id, or
	 *         an empty Optional if it doesn't exist
	 */
	@Override
	public ModuleDto findById(Long id) throws Exception {
		Optional<Module> moduleO = repository.findById(id);

		if (moduleO.isEmpty()) throw new Exception("Registro no encontrado");

		ModuleDto module = new ModuleDto();
		
		BeanUtils.copyProperties(moduleO.get(), module);
		
		module.setFormIds(formModuleRepository.getFormsByModuleId(id));
		
		return module;
	}

	/**
	 * Saves the specified Module entity to the database.
	 *
	 * @param module the Module entity to save
	 * @return the saved Module entity
	 */
	@Transactional
	@Override
	public Module save(ModuleDto module) throws Exception {
		Module moduleToSaved = new Module();
		
		BeanUtils.copyProperties(module, moduleToSaved);
		
		moduleToSaved.setCreatedAt(LocalDateTime.now());
		
		Module moduleSaved = this.repository.save(moduleToSaved);
		
		List<FormModule> listFormsModules =  new ArrayList<FormModule>();
		
		for (Long formId : module.getFormIds()) {
			Optional<Form> form = formRepository.findById(formId);
			if (form.isEmpty()) throw new Exception("Formulario no encontrado");
			
			FormModule formModuleToSave = new FormModule();
			
			formModuleToSave.setModuleId(moduleSaved);
			formModuleToSave.setFormId(form.get());
			
			listFormsModules.add(formModuleToSave);
		}
		
		formModuleRepository.saveAll(listFormsModules);
		
		return moduleSaved;
	}

	/**
	 * Update specified register
	 *
	 * @param id       the id entity to Update
	 * @param module the Module entity to Update
	 * @return the Update Module entity
	 */
	@Transactional
	@Override
	public void update(Long id, ModuleDto module) throws Exception {
		Optional<Module> moduleSearch = repository.findById(id);

		if (moduleSearch.isEmpty())
			throw new Exception("Registro no encontrado");

		Module moduleSaved = moduleSearch.get();

		BeanUtils.copyProperties(module, moduleSaved, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

		moduleSaved.setUpdatedAt(LocalDateTime.now());

		this.repository.save(moduleSaved);
		
		this.formModuleRepository.deleteByModuleId(id);
		
		List<FormModule> listFormsModules =  new ArrayList<FormModule>();
		
		for (Long formId : module.getFormIds()) {
			Optional<Form> form = formRepository.findById(formId);
			if (form.isEmpty()) throw new Exception("Formulario no encontrado");
			
			FormModule formModuleToSave = new FormModule();
			
			formModuleToSave.setFormId(form.get());
			formModuleToSave.setModuleId(moduleSaved);
			
			listFormsModules.add(formModuleToSave);
		}
		
		formModuleRepository.saveAll(listFormsModules);
	}

	/**
	 * Deletes the Module entity with the specified id from the database.
	 *
	 * @param id the id of the Module entity to delete
	 */
	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		Optional<Module> moduleOptional = repository.findById(id);

		if (moduleOptional.isEmpty())
			throw new Exception("Registro no encontrado");

		Module moduleSave = moduleOptional.get();
		moduleSave.setDeletedAt(LocalDateTime.now());
		this.repository.save(moduleSave);
		
		this.formModuleRepository.deleteByModuleId(id);
	}
}
