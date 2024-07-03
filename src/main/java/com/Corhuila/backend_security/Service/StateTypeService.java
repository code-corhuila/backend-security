package com.Corhuila.backend_security.Service;

/* #region Import */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.IStateTypeDto;
import com.Corhuila.backend_security.Entity.StateType;
import com.Corhuila.backend_security.IRepository.IStateTypeRepository;
import com.Corhuila.backend_security.IService.IStateTypeService;

import com.Corhuila.backend_security.Utils.BasicReportGenerator;


/**
 * This class provides the implementation for the IStateTypeService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class StateTypeService  extends BaseModelService<StateType> implements IStateTypeService {
	
	@Autowired
	private IStateTypeRepository repository;
	
	@Autowired
	private BasicReportGenerator<IStateTypeDto> _basicReportGenerator;
	
	@Override
	public Page<IStateTypeDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}
	
	@Override
	public byte[] export(String reportType) throws Exception {
		List<IStateTypeDto> data = this.repository.getDataToExport();
		
		if (reportType.equals("pdf")) {
			return _basicReportGenerator.exportToPdf(data, "BasicReport", "Tipo de Estado");  
		} else if (reportType.equals("excel")) {
			return _basicReportGenerator.exportToXls(data, "BasicReport", "Tipo de Estado");
		} 
		throw new Exception("No se encontró el tipo de reporte seleccionado");
	}
}
