package com.corhuila.servicesecurity.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.servicecorhuila.Dto.IChartLossDateContract;
import com.corhuila.servicecorhuila.Dto.IChartPeopleStateDto;
import com.corhuila.servicesecurity.IRepository.IChartRepository;
import com.corhuila.servicesecurity.IService.IChartService;

@Service
public class ChartService implements IChartService {
	
	@Autowired
	private IChartRepository _repository;

	@Override
	public List<IChartPeopleStateDto> getChartPeopleState() {
		return this._repository.getChartPeopleState();
	}

	@Override
	public List<IChartLossDateContract> getChartLossDateContract() {
		return this._repository.getChartLossDateContract();
	}

	
}
