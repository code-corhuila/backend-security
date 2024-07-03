package com.Corhuila.backend_security.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Corhuila.backend_security.Dto.IRoleDto;
import com.Corhuila.backend_security.Security.Entity.Role;

public interface IRoleService extends IBaseModelService<Role> {
	
    Page<IRoleDto> getDatatable(Pageable pageable, String search);
    
    public byte[] export(String reportType) throws Exception;
    
}