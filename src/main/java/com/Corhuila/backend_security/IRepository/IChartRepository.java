package com.Corhuila.backend_security.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Corhuila.backend_security.Dto.IChartLossDateContract;
import com.Corhuila.backend_security.Dto.IChartPeopleStateDto;
import com.Corhuila.backend_security.Entity.People;

@Repository
public interface IChartRepository extends JpaRepository<People, Long> {

	@Query(value = "select\r\n"
			+ "	s.\"name\" as stateName,\r\n"
			+ "	count(p.id) as quantity\r\n"
			+ "from parameter.people p\r\n"
			+ "inner join parameter.state s on s.id = p.state_id\r\n"
			+ "where p.deleted_at is null\r\n"
			+ "group by s.\"name\"\r\n"
			+ "order by s.\"name\"", nativeQuery = true)
	List<IChartPeopleStateDto> getChartPeopleState();
	
	@Query(value = "select * "
			+ "from parameter.employee ", nativeQuery = true)
	List<IChartLossDateContract> getChartLossDateContract();
	
	
}
