package com.Corhuila.backend_security.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Corhuila.backend_security.Dto.IDocumentTypeDto;
import com.Corhuila.backend_security.Entity.DocumentType;
import com.Corhuila.backend_security.IRepository.IDocumentTypeRepository;
import com.Corhuila.backend_security.IService.IDocumentTypeService;
import com.Corhuila.backend_security.Utils.BasicReportGenerator;

/**
* This class provides the implementation for the IDocumentTypeService interface.
* It's annotated with @Service to indicate that it's a Spring service component.
*/
@Service
public class DocumentTypeService extends BaseModelService<DocumentType> implements IDocumentTypeService {
	
	/**
	* Injects an instance of IDocumentTypeRepository into this class.
	*/
    @Autowired
	private IDocumentTypeRepository repository;
    
    @Autowired
	private BasicReportGenerator<IDocumentTypeDto> _basicReportGenerator;
    
    @Override
	public Page<IDocumentTypeDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}    
	
	@Override
	public byte[] export(String reportType) throws Exception {
		List<IDocumentTypeDto> data = this.repository.getDataToExport();
		
		if (reportType.equals("pdf")) {
			return _basicReportGenerator.exportToPdf(data, "BasicReport", "Tipo de Documento");  
		} else if (reportType.equals("excel")) {
			return _basicReportGenerator.exportToXls(data, "BasicReport", "Tipo de Documento");
		} 
		throw new Exception("No se encontró el tipo de reporte seleccionado");
	}
}
