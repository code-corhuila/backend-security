package com.Corhuila.backend_security.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IPeopleDto;
import com.Corhuila.backend_security.Entity.People;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the People entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface IPeopleRepository extends JpaRepository<People, Long> {
	
	@Query(value = " SELECT "
				 + "	pe.id, "
				 + "	dt.name AS documentType, "
				 + "	pe.document_number AS documentNumber, "
				 + "	pe.name, "
				 + "	pe.surname, "
				 + "	pe.mail, "
				 + "	pe.gender, "
				 + "	pe.state "
				 + " FROM parameter.people pe "
				 + " INNER JOIN parameter.document_type dt ON dt.id = pe.document_type_id"
				 + " WHERE pe.deleted_at is null and UPPER(CONCAT(dt.name,pe.document_number,pe.name,pe.surname,pe.mail,pe.gender)) " 
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IPeopleDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT "
				 + "	pe.id, "
				 + "	dt.name AS documentType, "
				 + "	pe.document_number AS documentNumber, "
				 + "	pe.name, "
				 + "	pe.surname, "
				 + "	pe.mail, "
				 + "	pe.gender, "
				 + "	pe.state "
				 + " FROM parameter.people pe "
				 + " INNER JOIN parameter.document_type dt ON dt.id = pe.document_type_id"
				 + " WHERE pe.deleted_at is null ", nativeQuery = true)
	List<IPeopleDto> getDataToExport();
	
	@Query(value = " SELECT "
				 + "	pe.id, "
				 + "	dt.name AS documentType, "
				 + "	pe.document_number AS documentNumber, "
				 + "	pe.name, "
				 + "	pe.surname, "
				 + "	pe.mail, "
				 + "	pe.gender, "
				 + "	pe.state "
				 + " FROM parameter.people pe "
				 + " WHERE pe.id = :id ", nativeQuery = true)
	Optional<IPeopleDto> getHeadquarterById(@Param("id") Long id);

	@Query(value = " SELECT  * "
			 + " FROM parameter.people "
			 + " WHERE document_number = :document_number ", nativeQuery = true)
	Optional<People> getPeopleByDocumentNumber(@Param("document_number") String documentNumber);


	List<People> findByStateTrue();

}