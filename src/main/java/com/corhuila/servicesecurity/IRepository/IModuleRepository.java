package com.corhuila.servicesecurity.IRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corhuila.servicecorhuila.Dto.IModuleDto;
import com.spa.spabackend.Entity.Module;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the Module entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/

@Repository
public interface IModuleRepository extends JpaRepository<Module, Long> {
	@Query(value = "SELECT m.*, STRING_AGG(f.name, ',') AS forms FROM security.module m\r\n"
			+ "LEFT JOIN security.form_module fm ON fm.module_id = m.id\r\n"
			+ "LEFT JOIN security.form f ON f.id = fm.form_id\r\n"
			+ "WHERE m.deleted_at is null and UPPER(CONCAT(m.name,m.color,m.icon,m.path,m.state)) LIKE CONCAT('%', UPPER(:search), '%')\r\n"
			+ "GROUP BY m.id, m.name, m.state, m.color, m.icon, m.path", nativeQuery = true)
	Page<IModuleDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = "SELECT m.*, STRING_AGG(f.name, ',') AS forms FROM security.module m\r\n"
			+ "LEFT JOIN security.form_module fm ON fm.module_id = m.id\r\n"
			+ "LEFT JOIN security.form f ON f.id = fm.form_id\r\n"
			+ "WHERE m.deleted_at is null "
			+ "GROUP BY m.id, m.name, m.state, m.color, m.icon, m.path", nativeQuery = true)
	List<IModuleDto> getDataToExport();

	List<Module> findByStateTrue();
	
	@Query(value = "SELECT m.name, m.color, m.path, m.icon FROM security.user_role ur "
			+ "INNER JOIN security.role r ON r.id = ur.role_id AND r.state = true "
			+ "INNER JOIN security.role_form rf ON rf.role_id = r.id "
			+ "INNER JOIN security.form_module fm ON fm.form_id = rf.form_id "
			+ "INNER JOIN security.module m ON m.id = fm.module_id AND m.state = true "
			+ "WHERE ur.user_id = :userid AND r.deleted_at IS NULL AND m.deleted_at IS NULL "
			+ "group by m.name, m.color, m.path, m.icon ", nativeQuery = true)
	List<IModuleDto> findByUserId(@Param("userid") Long userId);

}