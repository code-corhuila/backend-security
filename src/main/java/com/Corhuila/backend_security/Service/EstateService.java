package com.Corhuila.backend_security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.IEstateDto;
import com.Corhuila.backend_security.Entity.Estate;
import com.Corhuila.backend_security.IRepository.IEstateRepository;
import com.Corhuila.backend_security.IService.IEstateService;
import com.Corhuila.backend_security.Utils.BasicReportGenerator;

/**
 * This class provides the implementation for the IEstateService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class EstateService extends BaseModelService<Estate> implements IEstateService {
	
	/**
	 * Injects an instance of IEstateRepository into this class.
	 */
	@Autowired
	private IEstateRepository repository;
	
	@Autowired
	private BasicReportGenerator<IEstateDto> _basicReportGenerator;

	@Override
	public Page<IEstateDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}	
	
	@Override
	public List<IEstateDto> findByCountryId(Long countryId) {
		return this.repository.getByCountryId(countryId);
	}	
	
	@Override
	public byte[] export(String reportType) throws Exception {
		List<IEstateDto> data = this.repository.getDataToExport();
		
		if (reportType.equals("pdf")) {
			return _basicReportGenerator.exportToPdf(data, "EstateReport", "Departamentos");  
		} else if (reportType.equals("excel")) {
			return _basicReportGenerator.exportToXls(data, "EstateReport", "Departamentos");
		} 
		throw new Exception("No se encontró el tipo de reporte seleccionado");
	}
}
