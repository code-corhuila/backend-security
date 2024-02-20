package com.corhuila.servicesecurity.IRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corhuila.servicecorhuila.Dto.ICountryDto;
import com.spa.spabackend.Entity.Country;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the Country entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface ICountryRepository extends IBaseModelRepository<Country, Long> {
	
	@Query(value = " SELECT id, name, state FROM parameter.country "
				 + " WHERE deleted_at IS NULL AND UPPER(CONCAT(name, state)) "
				 + " LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<ICountryDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT id, name, state FROM parameter.country WHERE deleted_at IS NULL ", nativeQuery = true)
	List<ICountryDto> getDataToExport();
	
	List<Country> findByStateTrue();
}