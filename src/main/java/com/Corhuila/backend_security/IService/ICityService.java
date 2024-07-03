package com.Corhuila.backend_security.IService;

import java.util.List;

import com.Corhuila.backend_security.Dto.ICityDto;
import com.Corhuila.backend_security.Entity.City;

/**
* This interface defines the necessary methods to handle the City entity.
*/
public interface ICityService extends IBaseGenericService<City, ICityDto> {
	
	public ICityDto findByIdCustom(Long id) throws Exception;
	
	public List<ICityDto> getByEstateId(Long estateId);
}

