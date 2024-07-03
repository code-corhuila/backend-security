package com.Corhuila.backend_security.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Entity.FormModule;

	/**
	* This interface extends the JpaRepository interface and provides additional methods to handle the FormModule entity.
	* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
	*/
	@Repository
	public interface IFormModuleRepository extends JpaRepository<FormModule, Long> {
		
		@Modifying
		@Query(value = "DELETE FROM security.form_module WHERE form_id = ?1", nativeQuery = true)
		void deleteByFormId(Long formId);
		
		@Modifying
		@Query(value = "DELETE FROM security.form_module WHERE module_id = ?1", nativeQuery = true)
		void deleteByModuleId(Long moduleId);
		
		@Query(value = "SELECT module_id FROM security.form_module WHERE form_id = ?1", nativeQuery = true)
		List<Long> getModulesByFormId(Long formId);
		
		@Query(value = "SELECT form_id FROM security.form_module WHERE module_id = ?1", nativeQuery = true)
		List<Long> getFormsByModuleId(Long moduleId);

	}