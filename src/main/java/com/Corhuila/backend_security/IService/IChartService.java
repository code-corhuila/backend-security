package com.Corhuila.backend_security.IService;

import java.util.List;

import com.Corhuila.backend_security.Dto.IChartLossDateContract;
import com.Corhuila.backend_security.Dto.IChartPeopleStateDto;

public interface IChartService {

	List<IChartPeopleStateDto> getChartPeopleState();
	List<IChartLossDateContract> getChartLossDateContract();
	
}
