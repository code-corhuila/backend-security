package com.corhuila.servicesecurity.IRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corhuila.servicecorhuila.Dto.IFileDto;
import com.spa.spabackend.Entity.File;
import com.spa.spabackend.Entity.Headquarter;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the File entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/
@Repository
public interface IFileRepository extends IBaseModelRepository<File, Long> {
	
	@Query(value = " SELECT "
				 + " id, "
				 + " name, "
				 + " file_extension as fileExtension, "
				 + " table_name as tableName, "
				 + " table_id as tableId, "
				 + " archive, "
				 + " state "
				 + " FROM parameter.file "
				 + " WHERE table_name = :tablename and table_id = :tableid and deleted_at is null "
				 + " and CONCAT(name, file_extension, table_name, table_id, state) "
				 + " LIKE CONCAT('%', :search, '%') ", nativeQuery = true)
	Page<IFileDto> getDatatable(Pageable pageable, 
								@Param("search") String search, 
								@Param("tablename") String tableName, 
								@Param("tableid") String foreignKey);
	
	@Query(value = " SELECT  C.id, C.name, C.state, E.name AS estate, CO.name AS country FROM parameter.city C "
			 + " INNER JOIN parameter.estate AS E ON E.id = C.estate_id "
			 + " INNER JOIN parameter.country AS CO ON CO.id = E.country_id "
			 + " WHERE C.deleted_at IS NULL ", nativeQuery = true)
	List<IFileDto> getDataToExport();
	
	List<File> findByStateTrue();

}