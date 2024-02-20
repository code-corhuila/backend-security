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

import com.corhuila.servicecorhuila.Dto.FormDto;
import com.corhuila.servicecorhuila.Dto.IFormDto;
import com.corhuila.servicesecurity.Entity.Form;
import com.corhuila.servicesecurity.Entity.FormModule;
import com.corhuila.servicesecurity.Entity.RoleForm;
import com.corhuila.servicesecurity.IRepository.IFormModuleRepository;
import com.corhuila.servicesecurity.IRepository.IFormRepository;
import com.corhuila.servicesecurity.IRepository.IModuleRepository;
import com.corhuila.servicesecurity.IRepository.IRoleFormRepository;
import com.corhuila.servicesecurity.IService.IFormService;
import com.corhuila.servicesecurity.Security.Entity.Role;
import com.corhuila.servicesecurity.Security.Repository.RoleRepository;
import com.corhuila.servicesecurity.Utils.GlobalConstants;

/**
 * This class provides the implementation for the IFormService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class FormService implements IFormService {
	/**
	 * Injects an instance of IFormRepository into this class.
	 */
	@Autowired
	private IFormRepository repository;
	
	@Autowired
	private IModuleRepository moduleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private IFormModuleRepository formModuleRepository;
	
	@Autowired
	private IRoleFormRepository roleFormRepository;

	/**
	 * Datatable data
	 * 
	 * @param pageable
	 * @param search
	 * @return datatable data
	 */
	@Override
	public Page<IFormDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

	/**
	 * Returns all Form entities from the database.
	 *
	 * @return a List of all Form entities
	 */
	@Override
	public List<Form> all() {
		return repository.findByStateTrueAndDeletedAtIsNull();
	}

	/**
	 * Returns an Optional containing the Form entity with the specified id, if
	 * it exists in the database.
	 *
	 * @param id the id of the Form entity to retrieve
	 * @return an Optional containing the Form entity with the specified id, or
	 *         an empty Optional if it doesn't exist
	 */
	@Override
	public FormDto findById(Long id) throws Exception {
		Optional<Form> form = repository.findById(id);

		if (form.isEmpty())
			throw new Exception("Registro no encontrado");
		
		FormDto formDto = new FormDto();
		
		BeanUtils.copyProperties(form.get(), formDto);
		formDto.setModuleIds(formModuleRepository.getModulesByFormId(id));
		formDto.setRoleIds(roleFormRepository.getRolesByFormId(id));

		return formDto;
	}

	/**
	 * Saves the specified Form entity to the database.
	 *
	 * @param form the Form entity to save
	 * @return the saved Form entity
	 */
	@Transactional
	@Override
	public Form save(FormDto form) throws Exception {
		Form formToSaved = new Form();
		
		BeanUtils.copyProperties(form, formToSaved);
		
		
		formToSaved.setCreatedAt(LocalDateTime.now());
		Form formSaved = repository.save(formToSaved);
		
		List<FormModule> listFormsModules =  new ArrayList<FormModule>();
		
		for (Long moduleId : form.getModuleIds()) {
			Optional<com.corhuila.servicesecurity.Entity.Module> module = moduleRepository.findById(moduleId);
			if (module.isEmpty()) throw new Exception("Modulo no encontrado");
			
			FormModule formModuleToSave = new FormModule();
			
			formModuleToSave.setFormId(formSaved);
			formModuleToSave.setModuleId(module.get());
			
			listFormsModules.add(formModuleToSave);
		}
		
		formModuleRepository.saveAll(listFormsModules);
		
		List<RoleForm> listRoleForm =  new ArrayList<RoleForm>();
		
		for (Long roleId : form.getRoleIds()) {
			Optional<Role> role = roleRepository.findById(roleId);
			if (role.isEmpty()) throw new Exception("Rol no encontrado");
			
			RoleForm roleFormToSave = new RoleForm();
			
			roleFormToSave.setFormId(formSaved);
			roleFormToSave.setRoleId(role.get());
			
			listRoleForm.add(roleFormToSave);
		}
		
		roleFormRepository.saveAll(listRoleForm);
		
		return formSaved;
	}

	/**
	 * Update specified register
	 *
	 * @param id       the id entity to Update
	 * @param form the Form entity to Update
	 * @return the Update Form entity
	 */
	@Transactional
	@Override
	public void update(Long id, FormDto form) throws Exception {
		Optional<Form> formSearch = repository.findById(id);

		if (formSearch.isEmpty())
			throw new Exception("Registro no encontrado");

		Form formSaved = formSearch.get();

		BeanUtils.copyProperties(form, formSaved, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

		formSaved.setUpdatedAt(LocalDateTime.now());

		this.repository.save(formSaved);
		
		
		formModuleRepository.deleteByFormId(formSaved.getId());
		
		List<FormModule> listFormsModules =  new ArrayList<FormModule>();
		
		for (Long moduleId : form.getModuleIds()) {
			Optional<com.corhuila.servicesecurity.Entity.Module> module = moduleRepository.findById(moduleId);
			if (module.isEmpty()) throw new Exception("Modulo no encontrado");
			
			FormModule formModuleToSave = new FormModule();
			
			formModuleToSave.setFormId(formSaved);
			formModuleToSave.setModuleId(module.get());
			
			listFormsModules.add(formModuleToSave);
		}
		
		formModuleRepository.saveAll(listFormsModules);
		
		roleFormRepository.deleteByFormId(formSaved.getId());
		
		List<RoleForm> listRoleForm =  new ArrayList<RoleForm>();
		
		for (Long roleId : form.getRoleIds()) {
			Optional<Role> role = roleRepository.findById(roleId);
			if (role.isEmpty()) throw new Exception("Rol no encontrado");
			
			RoleForm roleFormToSave = new RoleForm();
			
			roleFormToSave.setFormId(formSaved);
			roleFormToSave.setRoleId(role.get());
			
			listRoleForm.add(roleFormToSave);
		}
		
		roleFormRepository.saveAll(listRoleForm);
	}

	/**
	 * Deletes the Form entity with the specified id from the database.
	 *
	 * @param id the id of the Form entity to delete
	 */
	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		Optional<Form> formOptional = repository.findById(id);

		if (formOptional.isEmpty())
			throw new Exception("Registro no encontrado");

		Form formSave = formOptional.get();
		formSave.setDeletedAt(LocalDateTime.now());
		this.repository.save(formSave);
		
		formModuleRepository.deleteByFormId(id);
		roleFormRepository.deleteByFormId(id);
	}
}
