package org.farynaa.servermanager.business.service;


/**
 * @author devil
 *
 */
public interface ServerService {

	void addServer(String filename);
	
	void deleteServer(Long id);
	
	void editServer(Long id, String newName);
	
	void listServers();
	
	void countServers();
	
}
