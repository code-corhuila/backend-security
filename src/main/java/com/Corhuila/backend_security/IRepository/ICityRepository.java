package com.Corhuila.backend_security.IRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.ICityDto;
import com.Corhuila.backend_security.Entity.City;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the CityRepository entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface ICityRepository extends IBaseModelRepository<City, Long> {
	@Query(value = " SELECT  C.id, C.name, C.state, E.name AS estate, CO.name AS country FROM parameter.city C "
				 + " INNER JOIN parameter.estate AS E ON E.id = C.estate_id "
				 + " INNER JOIN parameter.country AS CO ON CO.id = E.country_id "
				 + " WHERE C.deleted_at IS NULL AND UPPER(CONCAT(C.name, C.state, E.name, E.state, CO.name, CO.state)) "
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<ICityDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT  C.id, C.name, C.state, E.name AS estate, CO.name AS country FROM parameter.city C "
			 + " INNER JOIN parameter.estate AS E ON E.id = C.estate_id "
			 + " INNER JOIN parameter.country AS CO ON CO.id = E.country_id "
			 + " WHERE C.deleted_at IS NULL ", nativeQuery = true)
	List<ICityDto> getDataToExport();

	@Query(value = " SELECT  C.id, C.name, C.state, C.estate_id AS estateId, E.country_id AS countryId FROM parameter.city C "
				 + " INNER JOIN parameter.estate AS E ON E.id = C.estate_id "
				 + " WHERE C.id = :id ", nativeQuery = true)
	Optional<ICityDto> getCityById(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM parameter.city WHERE estate_id = :estateId AND state = true AND deleted_at IS NULL", nativeQuery = true)
	List<ICityDto> getByEstateId(@Param("estateId") Long estateId);

	List<City> findByStateTrue();

}