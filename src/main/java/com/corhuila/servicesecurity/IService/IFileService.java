package com.corhuila.servicesecurity.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.corhuila.servicecorhuila.Dto.IFileDto;
import com.spa.spabackend.Entity.File;


/**
* This interface defines the necessary methods to handle the File entity.
*/
public interface IFileService extends IBaseModelService<File> {
	
	public Page<IFileDto> getDatatable(Pageable pageable, String search, String tableName, String foreignKey);
	   
    public byte[] export(String reportType) throws Exception;
}
