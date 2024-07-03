package com.Corhuila.backend_security.Security.Dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Corhuila.backend_security.Dto.IFormDto;
import com.Corhuila.backend_security.Dto.IModuleDto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
	
	private Long id;
	
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    private String token;
    
    private Set<Long> roles = new HashSet<>();
    
    private List<IModuleDto> modulos;
    
    private List<IFormDto> formularios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }

	public List<IModuleDto> getModulos() {
		return modulos;
	}

	public void setModulos(List<IModuleDto> modulos) {
		this.modulos = modulos;
	}

	public List<IFormDto> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<IFormDto> formularios) {
		this.formularios = formularios;
	}
}
