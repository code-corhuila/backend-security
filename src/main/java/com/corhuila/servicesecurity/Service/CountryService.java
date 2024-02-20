package com.corhuila.servicesecurity.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.corhuila.servicecorhuila.Dto.ICountryDto;
import com.corhuila.servicesecurity.Entity.Country;
import com.corhuila.servicesecurity.IRepository.ICountryRepository;
import com.corhuila.servicesecurity.IService.ICountryService;
import com.corhuila.servicesecurity.Utils.BasicReportGenerator;

/**
* This class provides the implementation for the ICountryService interface.
* It's annotated with @Service to indicate that it's a Spring service component.
*/
@Service
public class CountryService extends BaseModelService<Country> implements ICountryService {
	
	/**
	* Injects an instance of ICountryRepository into this class.
	*/
    @Autowired
	private ICountryRepository repository;
    
    @Autowired
	private BasicReportGenerator<ICountryDto> _basicReportGenerator;

    @Override
	public Page<ICountryDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}    
	
	@Override
	public byte[] export(String reportType) throws Exception {
		List<ICountryDto> data = this.repository.getDataToExport();
		
		if (reportType.equals("pdf")) {
			return _basicReportGenerator.exportToPdf(data, "BasicReport", "Paises");  
		} else if (reportType.equals("excel")) {
			return _basicReportGenerator.exportToXls(data, "BasicReport", "Paises");
		} 
		throw new Exception("No se encontró el tipo de reporte seleccionado");
	}

	
}
