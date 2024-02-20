package com.corhuila.servicesecurity.IService;

import java.util.List;

import com.corhuila.servicecorhuila.Dto.IChartLossDateContract;
import com.corhuila.servicecorhuila.Dto.IChartPeopleStateDto;

public interface IChartService {

	List<IChartPeopleStateDto> getChartPeopleState();
	List<IChartLossDateContract> getChartLossDateContract();
	
}
