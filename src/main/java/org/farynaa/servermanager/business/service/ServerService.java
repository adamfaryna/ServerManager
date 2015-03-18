package org.farynaa.servermanager.business.service;

import java.util.List;

import org.farynaa.servermanager.business.model.entity.Server;


/**
 * @author devil
 *
 */
public interface ServerService {

	void addServer(String filename);
	
	void deleteServer(Long id);
	
	void editServer(Long id, String newName);
	
	List<Server> listServers();
	
	int countServers();
	
}
