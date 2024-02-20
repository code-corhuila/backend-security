package com.corhuila.servicesecurity.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.corhuila.servicecorhuila.Dto.IRoleDto;
import com.corhuila.servicesecurity.Security.Entity.Role;

public interface IRoleService extends IBaseModelService<Role> {
	
    Page<IRoleDto> getDatatable(Pageable pageable, String search);
    
    public byte[] export(String reportType) throws Exception;
    
}