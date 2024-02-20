package com.corhuila.servicesecurity.Entity;

import jakarta.persistence.Column;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseGeneric extends BaseModel{

    @Column(name = "name", nullable = false, length = 100)
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}    
}

