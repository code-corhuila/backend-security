package com.Corhuila.backend_security.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IStateDto;
import com.Corhuila.backend_security.Entity.State;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the State entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface IStateRepository extends IBaseModelRepository<State, Long> {
	
	@Query(value = " SELECT e.id, e.name, e.state, et.name as stateType FROM parameter.state AS e "
				 + " INNER JOIN parameter.state_type AS et ON et.id = e.state_type_id "
				 + " WHERE e.deleted_at is null AND UPPER(CONCAT(e.name, e.state, et.name)) "
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IStateDto> getDatatable(Pageable pageable, @Param("search") String search);	
	
	@Query(value = " SELECT e.id, e.name, e.state, et.name as stateType "
				 + " FROM parameter.state AS e "
				 + " INNER JOIN parameter.state_type AS et ON et.id = e.state_type_id "
				 + " WHERE e.deleted_at is null ", nativeQuery = true)
	List<IStateDto> getDataToExport();
	
	@Query(value = " SELECT e.id, e.name, e.state "
			 	 + " FROM parameter.state AS e "
				 + " WHERE e.id = :id ", nativeQuery = true)
	Optional<IStateDto> getStateById(@Param("id") Long id);
	
	@Query(value = " SELECT id, name, state, state_type_id"
				 + " FROM parameter.state "
				 + " WHERE state_type_id = :statetypeid AND state = true AND "
				 + " deleted_at IS NULL", nativeQuery = true)
	List<IStateDto> getByStateTypeId(@Param("statetypeid") Long StateTypeId);

}
