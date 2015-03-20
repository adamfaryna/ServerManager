package org.farynaa.servermanager.business.service;

import java.io.InputStream;
import java.util.List;

import org.farynaa.servermanager.business.model.entity.Server;


/**
 * Service for operations on servers.
 * 
 * @author adamfaryna@gmail.com
 */
public interface ServerService {

	void addServer(InputStream serverSpecFile);
	
	void deleteServer(Long id);
	
	void editServer(Long id, String newName);
	
	List<Server> listServers();
	
	int countServers();
	
}
