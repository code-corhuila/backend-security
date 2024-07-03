package com.Corhuila.backend_security.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Corhuila.backend_security.Dto.IPeopleDto;
import com.Corhuila.backend_security.Entity.People;


/**
* This interface defines the necessary methods to handle the People entity.
*/
public interface IPeopleService {
	
	/**
	 * Datatable data 
	 * @param pageable
	 * @param search
	 * @return datatable data
	 */
	public Page<IPeopleDto> getDatatable(Pageable pageable, String search);

	/**
	* Returns a list of all peoples.
	*
	* @return a list of People objects
	*/
	public List<People> all();
    
    /**
	* Returns the people with the specified id.
	*
	* @param id the id of the people to search for
	* @return an Optional object containing the found People or null if not found
	*/
    public People findById(Long id) throws Exception;
    
    /**
	* Saves the specified people.
	*
	* @param people the People object to save
	* @return the saved People object
	*/
    public People save(People people) throws Exception;
    
    /**
     * Update a people
     * @param id
     * @param people
	 * @throws Exception
     */
    public void update(Long id, People people) throws Exception;
    
    /**
	* Deletes the people with the specified id.
	*
	* @param id the id of the people to delete
	*/
    public void delete(Long id) throws Exception;

	/**
	* Returns the people with the specified document_number.
	*
	* @param id the id of the people to search for
	* @return an Optional object containing the found People or null if not found
	*/
    public People getPeopleByDocumentNumber(String documentNumber) throws Exception;

}
