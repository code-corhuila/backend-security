package com.Corhuila.backend_security.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Entity.BaseModel;

@Repository
public interface IBaseModelRepository<T extends BaseModel, ID> extends JpaRepository<T, ID> {
	
	//List<T> findByStateTrue();

}
