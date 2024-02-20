package com.corhuila.servicesecurity.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spa.spabackend.Entity.BaseModel;

@Repository
public interface IBaseModelRepository<T extends BaseModel, ID> extends JpaRepository<T, ID> {
	
	//List<T> findByStateTrue();

}
