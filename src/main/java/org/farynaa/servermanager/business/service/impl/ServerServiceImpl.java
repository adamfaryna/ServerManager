package org.farynaa.servermanager.business.service.impl;

import java.util.List;

import org.farynaa.servermanager.business.dao.ServerDAO;
import org.farynaa.servermanager.business.exception.validation.console.NoServerOfIdInDatabaseException;
import org.farynaa.servermanager.business.model.entity.Server;
import org.farynaa.servermanager.business.model.xml.ServerXML;
import org.farynaa.servermanager.business.service.ServerService;
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
	public void addServer(String serverSpecFilename) {
		ServerXML serverXML = ServerXML.createFromFile(serverSpecFilename);
		Server serverEntity = Server.createFromXML(serverXML);
		serverDAO.save(serverEntity);
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
		String message = buildListServersMessage(servers);
		System.out.println(message);
	}

	private String buildListServersMessage(List<Server> servers) {
		String message = new String("Persisted servers:\n");
		if (servers.size() == 0) {
			message += "empty";
			
		} else {
			for (Server server : servers) {
				message += server.toString() + "\n";
			}			
		}
		return message;
	}
	
	@Transactional(readOnly = true)
	@Override
	public void countServers() {
		int count = serverDAO.count();
		String message = String.format("There are '%d' servers saved in database.", count);
		System.out.println(message);
	}
}
