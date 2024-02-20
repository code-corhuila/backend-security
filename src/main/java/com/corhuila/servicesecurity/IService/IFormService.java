package com.corhuila.servicesecurity.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.corhuila.servicecorhuila.Dto.FormDto;
import com.corhuila.servicecorhuila.Dto.IFormDto;
import com.spa.spabackend.Entity.Form;


/**
* This interface defines the necessary methods to handle the Form entity.
*/
public interface IFormService {
	
	/**
	 * Datatable data 
	 * @param pageable
	 * @param search
	 * @return datatable data
	 */
	public Page<IFormDto> getDatatable(Pageable pageable, String search);

	/**
	* Returns a list of all forms.
	*
	* @return a list of Form objects
	*/
	public List<Form> all();
    
    /**
	* Returns the form with the specified id.
	*
	* @param id the id of the form to search for
	* @return an Optional object containing the found Form or null if not found
	*/
    public FormDto findById(Long id) throws Exception;
    
    /**
	* Saves the specified form.
	*
	* @param form the Form object to save
	* @return the saved Form object
	*/
    public Form save(FormDto form) throws Exception;
    
    /**
     * Update a form
     * @param id
     * @param form
	 * @throws Exception
     */
    public void update(Long id, FormDto form) throws Exception;
    
    /**
	* Deletes the form with the specified id.
	*
	* @param id the id of the form to delete
	*/
    public void delete(Long id) throws Exception;
}
