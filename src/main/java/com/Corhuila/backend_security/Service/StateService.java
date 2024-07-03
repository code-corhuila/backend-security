package com.Corhuila.backend_security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.IStateDto;
import com.Corhuila.backend_security.Entity.State;
import com.Corhuila.backend_security.IRepository.IStateRepository;
import com.Corhuila.backend_security.IService.IStateService;
import com.Corhuila.backend_security.Utils.BasicReportGenerator;

/**
 * This class provides the implementation for the IStateService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class StateService extends BaseModelService<State> implements IStateService {
	

	/**
	 * Injects an instance of IStateRepository into this class.
	 */
	@Autowired
	private IStateRepository repository;
	
	@Autowired
	private BasicReportGenerator<IStateDto> _basicReportGenerator;



	@Override
	public Page<IStateDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

	
	@Override
	public List<IStateDto> getByStateTypeId(Long StateTypeId) {
		return this.repository.getByStateTypeId(StateTypeId);
	}	
	
	@Override
	public List<IStateDto> getByStateId(Long StateId) {
		return this.repository.getByStateTypeId(StateId);
	}	
	
	
	@Override
	public byte[] export(String reportType) throws Exception {
		List<IStateDto> data = this.repository.getDataToExport();
		
		if (reportType.equals("pdf")) {
			return _basicReportGenerator.exportToPdf(data, "StateReport", "Departamentos");  
		} else if (reportType.equals("excel")) {
			return _basicReportGenerator.exportToXls(data, "StateReport", "Departamentos");
		} 
		throw new Exception("No se encontró el tipo de reporte seleccionado");
	}
}
