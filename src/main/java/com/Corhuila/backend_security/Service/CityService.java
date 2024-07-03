package com.Corhuila.backend_security.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.ICityDto;
import com.Corhuila.backend_security.Entity.City;
import com.Corhuila.backend_security.IRepository.ICityRepository;
import com.Corhuila.backend_security.IService.ICityService;
import com.Corhuila.backend_security.Utils.BasicReportGenerator;

/**
 * This class provides the implementation for the ICityService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class CityService extends BaseModelService<City> implements ICityService {

	/**
	* Injects an instance of ICityRepository into this class.
	*/
    @Autowired
	public ICityRepository repository;
    
    @Autowired
    private BasicReportGenerator<ICityDto> _reportGenerator;

    @Override
	public Page<ICityDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

    @Override
	public List<ICityDto> getByEstateId(Long estateId) {
		return this.repository.getByEstateId(estateId);
	}    

	@Override
	public ICityDto findByIdCustom(Long id) throws Exception {
		Optional<ICityDto> cityO = repository.getCityById(id);
		
		if (cityO.isEmpty()) throw new Exception("Registro no encontrado");
		
		return cityO.get();
	}   
	
	@Override
	public byte[] export(String reportType) throws Exception {
		List<ICityDto> data = this.repository.getDataToExport();
		
		if (reportType.equals("pdf")) {
			return _reportGenerator.exportToPdf(data, "CityReport", "Ciudades");  
		} else if (reportType.equals("excel")) {
			return _reportGenerator.exportToXls(data, "CityReport", "Ciudades");
		} 
		throw new Exception("No se encontró el tipo de reporte seleccionado");
	}
	
}