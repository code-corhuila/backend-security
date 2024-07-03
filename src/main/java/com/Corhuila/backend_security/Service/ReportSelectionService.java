package com.Corhuila.backend_security.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.ReportEmployeeDto;
import com.Corhuila.backend_security.IService.IReportSelectionService;

@Service
public class ReportSelectionService implements IReportSelectionService {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Page<ReportEmployeeDto> getReportEmployee(Integer page, Integer cantidad, String search, Boolean gender, Long statusId, Long positionId) {
		List<ReportEmployeeDto> view = new ArrayList<ReportEmployeeDto>();
		String conector = "WHERE";
		
		String sqlColumns = "SELECT \r\n"
				+ "	CONCAT(pe.name, ' ', pe.surname) AS full_name,\r\n"
				+ "	dt.name AS document_type,\r\n"
				+ "	pe.document_number,\r\n"
				+ "	cex.name AS document_issuance_city,\r\n"
				+ "	pe.birth_date,\r\n"
				+ "	pe.gender,\r\n"
				+ "	ss.name AS scholarship,\r\n"
				+ "	cs.name AS civil_status,\r\n"
				+ "	bt.name AS blood_type,\r\n"
				+ "	po.name AS positions,\r\n"
				+ "	ci.name AS city,\r\n"
				+ "	pe.address,\r\n"
				+ "	pe.attendant_name,\r\n"
				+ "	pe.attending_phone\r\n";
		
		String sqlColumnsCount = "SELECT COUNT(*)";
		
		
		String sqlFrom = "FROM parameter.Employee AS em\r\n"
				+ "INNER JOIN parameter.People AS pe on em.People_Id = pe.Id \r\n"
				+ "INNER JOIN parameter.document_type AS dt on pe.document_type_Id = dt.Id \r\n"
				+ "INNER JOIN parameter.city AS cex on pe.document_issuance_city_id = cex.Id \r\n"
				+ "INNER JOIN parameter.scholarship AS ss ON ss.id = pe.scholarship_id\r\n"
				+ "INNER JOIN parameter.civil_status AS cs ON cs.id = pe.status_civil_id\r\n"
				+ "INNER JOIN parameter.blood_type AS bt ON bt.id = pe.blood_type_id\r\n"
				+ "INNER JOIN parameter.position AS po  ON po.id = pe.position_id\r\n"
				+ "INNER JOIN parameter.city AS ci ON ci.id = pe.city_id\r\n";
		
		StringBuilder queryBuilder = new StringBuilder();
		
		String sqlOrdenamiento = " ORDER BY em.id DESC ";
		String sqlPaginacion = " OFFSET :offset ROWS FETCH NEXT :tamanoPagina ROWS ONLY ";
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		
		if (gender != null) {
			queryBuilder.append(conector + " pe.gender = :gender ");
			conector = "AND";
			parametros.addValue("gender", gender);
		}
		
		if (statusId != null && !statusId.equals(Long.parseLong("0"))) {
			queryBuilder.append(conector + " pe.state_id = :stateid ");
			conector = "AND";
			parametros.addValue("stateid", statusId);
		}
		
		if (positionId != null && !positionId.equals(Long.parseLong("0"))) {
			queryBuilder.append(conector + " pe.position_id = :positionid ");
			conector = "AND";
			parametros.addValue("positionid", positionId);
		}
		
		if (search != null && !search.isEmpty()) {
			queryBuilder.append(conector + " AND CONCAT(pe.name, ' ', pe.surname,dt.name,pe.document_number,cex.name,pe.birth_date,pe.gender,ss.name,cs.name,bt.name,\r\n"
					+ "	po.name,ci.name,pe.address,pe.attendant_name,pe.attending_phone) LIKE CONCAT('%', :search, '%') ");
			parametros.addValue("search", positionId);
		}
		
		parametros.addValue("offset", page * cantidad);
		parametros.addValue("tamanoPagina", cantidad);
		
		view = jdbcTemplate.query( sqlColumns + sqlFrom + queryBuilder.toString() +  sqlOrdenamiento + sqlPaginacion, parametros, new RowMapper<ReportEmployeeDto>() {

			@Override
			public ReportEmployeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new ReportEmployeeDto(
						rs.getString("full_name"),
						rs.getString("document_type"),
						rs.getString("document_number"),
						rs.getString("document_issuance_city"),
						//LocalDate.parse(rs.getString("birth_date")),
						LocalDate.now(),
						"",
						rs.getString("gender"),
						rs.getString("scholarship"),
						rs.getString("civil_status"),
						rs.getString("blood_type"),
						rs.getString("positions"),
						rs.getString("city"),
						rs.getString("address"),
						rs.getString("attendant_name"),
						rs.getString("attending_phone"));
			}
		});
		
		Long totalRegistros = jdbcTemplate.queryForObject(sqlColumnsCount + sqlFrom + queryBuilder.toString(), parametros, Long.class);
		
		
		return new PageImpl<>(view, PageRequest.of(page , cantidad), totalRegistros);
		
	}

}
