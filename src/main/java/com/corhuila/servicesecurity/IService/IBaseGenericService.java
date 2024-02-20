package com.corhuila.servicesecurity.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.corhuila.servicesecurity.Entity.BaseGeneric;

public interface IBaseGenericService<T extends BaseGeneric, D> extends IBaseModelService<T> {
    
	Page<D> getDatatable(Pageable pageable, String search);

    byte[] export(String reportType) throws Exception;
}