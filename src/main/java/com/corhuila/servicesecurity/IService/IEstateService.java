package com.corhuila.servicesecurity.IService;

import java.util.List;

import com.corhuila.servicecorhuila.Dto.IEstateDto;
import com.spa.spabackend.Entity.Estate;

/**
* This interface defines the necessary methods to handle the Estate entity.
*/
public interface IEstateService extends IBaseGenericService<Estate, IEstateDto> {
	
	public List<IEstateDto> findByCountryId(Long countryId);
}

