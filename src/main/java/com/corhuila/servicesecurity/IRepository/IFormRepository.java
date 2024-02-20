package com.corhuila.servicesecurity.IRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corhuila.servicecorhuila.Dto.IFormDto;
import com.spa.spabackend.Entity.Form;

/**
* This interface extends the JpaRepository interface and provides additional methods to handle the Form entity.
* It inherits all the basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
*/

@Repository
public interface IFormRepository extends JpaRepository<Form, Long> {
	@Query(value = "SELECT f.*, STRING_AGG(m.name, ',') AS modules\r\n"
			+ " FROM security.form f\r\n"
			+ " LEFT JOIN security.form_module fm ON fm.form_id = f.id\r\n"
			+ " LEFT JOIN security.module m ON m.id = fm.module_id\r\n"
			+ " WHERE f.deleted_at is null AND \r\n"
			+ " UPPER(CONCAT(f.name, f.state, f.color, f.icon, f.path, f.section)) LIKE CONCAT('%', UPPER(:search), '%')\r\n"
			+ " GROUP BY f.id, f.name, f.state, f.color, f.icon, f.path, f.section", nativeQuery = true)
	Page<IFormDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = "SELECT f.*, STRING_AGG(m.name, ',') AS modules\r\n"
			+ " FROM security.form f\r\n"
			+ " LEFT JOIN security.form_module fm ON fm.form_id = f.id\r\n"
			+ " LEFT JOIN security.module m ON m.id = fm.module_id\r\n"
			+ " WHERE f.deleted_at is null \r\n"
			+ " GROUP BY f.id, f.name, f.state, f.color, f.icon, f.path, f.section", nativeQuery = true)
	List<IFormDto> getDataToExport();

	List<Form> findByStateTrueAndDeletedAtIsNull();
	
	@Query(value = "SELECT "
			+ "	f.id, "
			+ "	f.\"name\", "
			+ "	f.path, "
			+ "	f.section, "
			+ "	f.icon, "
			+ "	m.path AS modulePath "
			+ "FROM security.user_role ur "
			+ "INNER JOIN security.role_form rf ON rf.role_id = ur.role_id "
			+ "INNER JOIN security.form f ON f.id = rf.form_id AND f.deleted_at IS NULL "
			+ "INNER JOIN security.form_module fm ON fm.form_id = f.id "
			+ "INNER JOIN security.module m ON m.id = fm.module_id AND m.deleted_at IS NULL "
			+ "WHERE ur.user_id = :userid AND f.state = true "
			+ "ORDER BY m.path, f.section, f.name ", nativeQuery = true)
	List<IFormDto> getFormsByUser(@Param("userid") Long userid);

}