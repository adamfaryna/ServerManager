package org.farynaa.servermanager.service.impl;

import java.util.List;

import org.farynaa.servermanager.dao.ServerDAO;
import org.farynaa.servermanager.exception.console.NoServerOfIdInDatabaseException;
import org.farynaa.servermanager.model.Server;
import org.farynaa.servermanager.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author devil
 *
 */
@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerDAO serverDAO;
	
	
	@Transactional
	@Override
	public void addServer() {
		
		
	}

	@Transactional
	@Override
	public void deleteServer(Long id) {
		Server server = serverDAO.get(id);
		
		if (server == null) {
			throw new NoServerOfIdInDatabaseException(id);
		}
		
		serverDAO.delete(server);
	}

	@Transactional
	@Override
	public void editServer(Long id, String newName) {
		Server server = serverDAO.get(id);
		server.setName(newName);
		serverDAO.save(server);
	}

	@Transactional(readOnly = true)
	@Override
	public void listServers() {
		List<Server> servers = serverDAO.getAll();
		
		String message = new String("Persisted servers:\n");
		if (servers.size() == 0) {
			message += "none";
			
		} else {
			for (Server server : servers) {
				message += server.toString() + "\n";
			}			
		}
		
		System.out.println(message);
	}
}
