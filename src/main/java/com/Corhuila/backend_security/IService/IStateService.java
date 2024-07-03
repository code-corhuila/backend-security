package com.Corhuila.backend_security.IService;

import java.util.List;

import com.Corhuila.backend_security.Dto.IStateDto;
import com.Corhuila.backend_security.Entity.State;

/**
* This interface defines the necessary methods to handle the State entity.
*/
public interface IStateService extends IBaseGenericService<State, IStateDto> {
	
	public List<IStateDto> getByStateTypeId(Long StateTypeId);
    
    public List<IStateDto> getByStateId(Long StateId);
}
