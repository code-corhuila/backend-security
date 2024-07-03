package com.Corhuila.backend_security.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Entity.RoleForm;

@Repository
public interface IRoleFormRepository extends JpaRepository<RoleForm, Long> {

	@Modifying
	@Query(value = "DELETE FROM security.role_form WHERE form_id = ?1", nativeQuery = true)
	void deleteByFormId(Long formId);
	
	@Modifying
	@Query(value = "DELETE FROM security.role_form WHERE role_id = ?1", nativeQuery = true)
	void deleteByRoleId(Long roleId);
	
	@Query(value = "SELECT role_id FROM security.role_form WHERE form_id = ?1", nativeQuery = true)
	List<Long> getRolesByFormId(Long formId);
	
	@Query(value = "SELECT form_id FROM security.role_form WHERE role_id = ?1", nativeQuery = true)
	List<Long> getFormsByRoleId(Long roleId);
	
}