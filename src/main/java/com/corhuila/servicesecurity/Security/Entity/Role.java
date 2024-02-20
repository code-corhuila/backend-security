package com.corhuila.servicesecurity.Security.Entity;
import com.corhuila.servicesecurity.Entity.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "role", schema = "security")
public class Role extends BaseModel{

	@Column(name = "name", nullable = false)
	private String name;

	public Role() {
	}

	public Role(@NotNull String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
