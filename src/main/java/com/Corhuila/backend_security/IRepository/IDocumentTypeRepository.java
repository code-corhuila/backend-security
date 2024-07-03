package com.Corhuila.backend_security.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IDocumentTypeDto;
import com.Corhuila.backend_security.Entity.DocumentType;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the DocumentType entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface IDocumentTypeRepository extends IBaseModelRepository<DocumentType, Long> {
	
	@Query(value = " SELECT id, name, state FROM parameter.document_type "
				 + " WHERE deleted_at is null AND UPPER(CONCAT(name, state)) "
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IDocumentTypeDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT id, name, state FROM parameter.document_type "
				 + " WHERE deleted_at is null ", nativeQuery = true)
	List<IDocumentTypeDto> getDataToExport();

	@Query(value = " SELECT id, name, state FROM parameter.document_type "
				 + " WHERE C.id = :id ", nativeQuery = true)
	Optional<IDocumentTypeDto> getDocumentTypeById(@Param("id") Long id);

	List<DocumentType> findByStateTrue();
}