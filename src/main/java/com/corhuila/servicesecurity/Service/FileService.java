package com.corhuila.servicesecurity.Service;

/* #region Import */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.corhuila.servicecorhuila.Dto.IFileDto;
import com.corhuila.servicesecurity.Entity.File;
import com.corhuila.servicesecurity.IRepository.IFileRepository;
import com.corhuila.servicesecurity.IService.IFileService;


/**
 * This class provides the implementation for the IFileService interface.
 * It's annotated with @Service to indicate that it's a Spring service
 * component.
 */
@Service
public class FileService extends BaseModelService<File> implements IFileService {
	/**
	 * Injects an instance of IFileRepository into this class.
	 */
	@Autowired
	public IFileRepository repository;

	@Override
	public Page<IFileDto> getDatatable(Pageable pageable, String search, String tableName, String foreignKey) {
		return this.repository.getDatatable(pageable, search, tableName, foreignKey);
	}
	
	 @Override
		public byte[] export(String reportType) throws Exception {
			// TODO Auto-generated method stub
			return null;
	} 
}
