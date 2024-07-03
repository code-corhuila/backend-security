package com.Corhuila.backend_security.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Corhuila.backend_security.Entity.BaseGeneric;

public interface IBaseGenericService<T extends BaseGeneric, D> extends IBaseModelService<T> {
    
	Page<D> getDatatable(Pageable pageable, String search);

    byte[] export(String reportType) throws Exception;
}