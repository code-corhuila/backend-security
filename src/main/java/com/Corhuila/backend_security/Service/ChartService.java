package com.Corhuila.backend_security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.IChartLossDateContract;
import com.Corhuila.backend_security.Dto.IChartPeopleStateDto;
import com.Corhuila.backend_security.IRepository.IChartRepository;
import com.Corhuila.backend_security.IService.IChartService;

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
