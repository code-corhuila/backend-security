package com.corhuila.servicesecurity.IService;

import java.util.List;

import com.spa.spabackend.Entity.FormModule;


/**
* This interface defines the necessary methods to handle the FormModule entity.
*/
public interface IFormModuleService {

	/**
	* Returns a list of all formModules.
	*
	* @return a list of FormModule objects
	*/
	public List<FormModule> all();
    
    /**
	* Returns the formModule with the specified id.
	*
	* @param id the id of the formModule to search for
	* @return an Optional object containing the found FormModule or null if not found
	*/
    public FormModule findById(Long id) throws Exception;
    
    /**
	* Saves the specified formModule.
	*
	* @param formModule the FormModule object to save
	* @return the saved FormModule object
	*/
    public FormModule save(FormModule formModule);
    
    /**
     * Update a formModule
     * @param id
     * @param formModule
	 * @throws Exception
     */
    public void update(Long id, FormModule formModule) throws Exception;
    
    /**
	* Deletes the formModule with the specified id.
	*
	* @param id the id of the formModule to delete
	*/
    public void delete(Long id) throws Exception;
}
