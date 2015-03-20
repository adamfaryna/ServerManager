package org.farynaa.servermanager.business.service.impl;

import java.io.InputStream;
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
 * Implementation of {@link ServerService}.
 *  
 * @author adamfaryna@gmail.com
 */
@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerDAO serverDAO;
	
	
	@Transactional
	@Override
	public void addServer(InputStream serverSpecFile) {
		ServerXML serverXML = ServerXML.createFromFile(serverSpecFile);
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
		
		if (server == null) {
			throw new NoServerOfIdInDatabaseException(id);
		}
		
		server.setName(newName);
		serverDAO.save(server);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Server> listServers() {
		List<Server> servers = serverDAO.getAll();
		return servers;
	}
	
	@Transactional(readOnly = true)
	@Override
	public int countServers() {
		int count = serverDAO.count();
		return count;
	}
}
