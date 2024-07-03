package com.Corhuila.backend_security.Dto;

import java.util.List;

public class ModuleDto {
	
	private Long id;
	
    private String name;

    private String color;

    private String icon;

    private String path;

    private Boolean state;
    
    private List<Long> formIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public List<Long> getFormIds() {
		return formIds;
	}

	public void setFormIds(List<Long> formIds) {
		this.formIds = formIds;
	}
    
    
}
