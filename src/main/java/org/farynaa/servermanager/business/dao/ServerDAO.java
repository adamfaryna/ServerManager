package org.farynaa.servermanager.business.dao;

import java.util.List;

import org.farynaa.servermanager.business.model.entity.Server;

/**
 * DAO for {@link Server}
 * 
 * @author adamfaryna@gmail.com
 */
public interface ServerDAO {

	List<Server> getAll();
	
	int count();
	
	Server get(Long id);
	
	void save(Server server);
	
	void delete(Server server);
	
}
