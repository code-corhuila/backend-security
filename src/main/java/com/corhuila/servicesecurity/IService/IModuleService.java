package com.corhuila.servicesecurity.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




/**
* This interface defines the necessary methods to handle the Module entity.
*/
public interface IModuleService {
	
	/**
	 * Datatable data 
	 * @param pageable
	 * @param search
	 * @return datatable data
	 */
	public Page<IModuleDto> getDatatable(Pageable pageable, String search);

	/**
	* Returns a list of all modules.
	*
	* @return a list of Module objects
	*/
	public List<Module> all();
    
    /**
	* Returns the module with the specified id.
	*
	* @param id the id of the module to search for
	* @return an Optional object containing the found Module or null if not found
	*/
    public ModuleDto findById(Long id) throws Exception;
    
    /**
	* Saves the specified module.
	*
	* @param module the Module object to save
	* @return the saved Module object
	*/
    public Module save(ModuleDto module) throws Exception;
    
    /**
     * Update a module
     * @param id
     * @param module
	 * @throws Exception
     */
    public void update(Long id, ModuleDto module) throws Exception;
    
    /**
	* Deletes the module with the specified id.
	*
	* @param id the id of the module to delete
	*/
    public void delete(Long id) throws Exception;
}
