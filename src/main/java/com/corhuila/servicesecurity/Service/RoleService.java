package com.corhuila.servicesecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.corhuila.servicecorhuila.Dto.IRoleDto;
import com.corhuila.servicesecurity.IRepository.IRoleRepository;
import com.corhuila.servicesecurity.IService.IRoleService;
import com.corhuila.servicesecurity.Security.Entity.Role;

@Service
public class RoleService extends BaseModelService<Role> implements IRoleService {

    @Autowired
    private IRoleRepository repository;

    @Override
    public Page<IRoleDto> getDatatable(Pageable pageable, String search) {
        return repository.getDatatable(pageable, search);
    }
    
    @Override
   	public byte[] export(String reportType) throws Exception {
   		// TODO Auto-generated method stub
   		return null;
   	} 
}
