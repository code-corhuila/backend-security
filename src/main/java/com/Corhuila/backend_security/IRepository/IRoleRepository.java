package com.Corhuila.backend_security.IRepository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IRoleDto;
import com.Corhuila.backend_security.Security.Entity.Role;

/**
* This interface extends the IBaseModelRepository interface and provides additional methods to handle the entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
@Primary
public interface IRoleRepository extends IBaseModelRepository<Role, Long> {
	
	@Query(value = " SELECT id, name, state FROM security.role "
		     + " WHERE deleted_at IS NULL AND UPPER(CONCAT(name, state)) "
			 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IRoleDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT id, name, state FROM security.role WHERE deleted_at IS NULL ", nativeQuery = true)
	List<IRoleDto> getDataToExport();	
}
