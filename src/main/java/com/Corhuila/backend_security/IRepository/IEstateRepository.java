package com.Corhuila.backend_security.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IEstateDto;
import com.Corhuila.backend_security.Entity.Estate;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the Estate entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface IEstateRepository extends IBaseModelRepository<Estate, Long> {

	@Query(value = " SELECT"
				 + " E.id,"
				 + " E.name,"
				 + " E.state,"
				 + " CO.name AS country "
				 + " FROM parameter.estate AS E"
				 + " INNER JOIN parameter.country AS CO ON CO.id = E.country_id"
				 + " WHERE E.deleted_at is null AND UPPER(CONCAT(E.name, E.state, CO.name)) "
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IEstateDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT"
				 + " E.id,"
				 + " E.name,"
				 + " E.state,"
				 + " CO.name AS country "
				 + " FROM parameter.estate AS E"
				 + " INNER JOIN parameter.country AS CO ON CO.id = E.country_id"
				 + " WHERE E.deleted_at is null ", nativeQuery = true)
	List<IEstateDto> getDataToExport();
	
	@Query(value = " SELECT id, name, state, country_id AS countryId "
				 + " FROM parameter.estate "
				 + " WHERE id = :id ", nativeQuery = true)
	Optional<IEstateDto> getEstateById(@Param("id") Long id);

	@Query(value = " SELECT id, name, state, country_id AS countryId "
				 + " FROM parameter.estate "
				 + " WHERE country_id = :countryId AND state = true AND "
				 + " deleted_at IS NULL", nativeQuery = true)
	List<IEstateDto> getByCountryId(@Param("countryId") Long countryId);
	
	List<Estate> findByStateTrue();

}