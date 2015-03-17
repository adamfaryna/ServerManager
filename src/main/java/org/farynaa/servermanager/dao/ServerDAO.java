package org.farynaa.servermanager.dao;

import java.util.List;

import org.farynaa.servermanager.model.Server;

public interface ServerDAO {

	List<Server> getAll();
	
	Server get(Long id);
	
	void save(Server server);
	
	void delete(Server server);
	
}
