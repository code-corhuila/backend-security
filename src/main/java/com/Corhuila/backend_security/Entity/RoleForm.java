package com.Corhuila.backend_security.Entity;

import com.Corhuila.backend_security.Security.Entity.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_form", schema = "security")
public class RoleForm{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	private Role roleId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "form_id", nullable = false)
	private Form formId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Form getFormId() {
		return formId;
	}

	public void setFormId(Form formId) {
		this.formId = formId;
	}	
}
