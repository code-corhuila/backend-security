package com.Corhuila.backend_security.IRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IStateTypeDto;
import com.Corhuila.backend_security.Entity.StateType;


/**
* This interface extends the IBaseModelRepository interface and provides additional methods to handle the entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface IStateTypeRepository extends IBaseModelRepository<StateType, Long> {
	
	@Query(value = " SELECT id, name, state FROM parameter.state_type "
				 + " WHERE deleted_at is null AND UPPER(CONCAT(name, state)) "
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IStateTypeDto> getDatatable(Pageable pageable, @Param("search") String search);

	
	@Query(value = " SELECT id, name, state FROM parameter.state_type "
				 + " WHERE deleted_at is null ", nativeQuery = true)
	List<IStateTypeDto> getDataToExport();
	
	List<StateType> findByStateTrue();
	
}