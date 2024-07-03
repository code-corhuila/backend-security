package com.Corhuila.backend_security.IService;

import java.util.List;

import com.Corhuila.backend_security.Entity.BaseModel;

public interface IBaseModelService<T extends BaseModel>  {	
	
    List<T> all();
    
    List<T> findByStateTrue();
    
    T findById(Long id) throws Exception;
    
    T save(T entity) throws Exception;
    
    void update(Long id, T entity) throws Exception;
  
    void delete(Long id) throws Exception;
}