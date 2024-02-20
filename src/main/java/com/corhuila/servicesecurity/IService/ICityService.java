package com.corhuila.servicesecurity.IService;

import java.util.List;

import com.corhuila.servicecorhuila.Dto.ICityDto;
import com.corhuila.servicesecurity.Entity.City;


/**
* This interface defines the necessary methods to handle the City entity.
*/
public interface ICityService extends IBaseGenericService<City, ICityDto> {
	
	public ICityDto findByIdCustom(Long id) throws Exception;
	
	public List<ICityDto> getByEstateId(Long estateId);
}

