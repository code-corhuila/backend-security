package com.Corhuila.backend_security.IService;

import java.util.List;

import com.Corhuila.backend_security.Dto.IEstateDto;
import com.Corhuila.backend_security.Entity.Estate;

/**
* This interface defines the necessary methods to handle the Estate entity.
*/
public interface IEstateService extends IBaseGenericService<Estate, IEstateDto> {
	
	public List<IEstateDto> findByCountryId(Long countryId);
}

