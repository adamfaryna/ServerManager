package org.farynaa.servermanager.business.service.impl;

import java.io.InputStream;
import java.util.List;

import org.farynaa.servermanager.AbstractTestWithXML;
import org.farynaa.servermanager.business.dao.ServerDAO;
import org.farynaa.servermanager.business.exception.internal.XMLParseErrorException;
import org.farynaa.servermanager.business.exception.validation.console.NoServerOfIdInDatabaseException;
import org.farynaa.servermanager.business.exception.validation.console.PassedServerFileParameterNotExists;
import org.farynaa.servermanager.business.model.entity.Server;
import org.farynaa.servermanager.business.service.ServerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Integration tests for {@link ServerServiceImpl}.
 * 
 * @author adamfaryna@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class ServerServiceImplIntegrationTest extends AbstractTestWithXML {

	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ServerDAO serverDAO;
	
	@After
	public void cleanAllPersistedServers() {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			getCurrentHibernateSession().createQuery("delete from " + Server.class.getSimpleName()).executeUpdate();						
		}
	}

	private Session getCurrentHibernateSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Test(expected = PassedServerFileParameterNotExists.class)
	public void testAddServerFileNotExists() {
		InputStream notExistingFile = null;
		serverService.addServer(notExistingFile);
	}
	
	@Transactional
	@Test
	public void testAddServer() {	
		InputStream serverSpecFile = getCorrectServerSpecFileAsInputStream();
		Server expected = createNewServerCorrectInstance();
		
		serverService.addServer(serverSpecFile);
		assertServerWasPersisted(expected);
	}
	
	@Test(expected = XMLParseErrorException.class)
	public void testAddServerFileCorrupted() {
		InputStream corruptedServerSpecFile = getCorruptedServerSpecFileAsInputStream();
		serverService.addServer(corruptedServerSpecFile);
	}
	
	@Transactional
	@Test
	public void testDeleteServerExists() {
		Server serverToDelete = createNewServerCorrectInstance();
		serverDAO.save(serverToDelete);
		
		serverService.deleteServer(serverToDelete.getId());
		
		Server result = serverDAO.get(serverToDelete.getId());
		Assert.assertNull(result);
	}

	private Server createNewServerCorrectInstance() {
		Server server = new Server();
		server.setId(1L);
		server.setName("name");
		return server;
	}
	
	@Transactional
	@Test(expected = NoServerOfIdInDatabaseException.class)
	public void testDeleteServerNotExists() {
		Long notExistingServerId = 1L;
		serverService.deleteServer(notExistingServerId);
	}
	
	@Transactional
	@Test
	public void testEditServerExists() {
		Server startServerInstance = createNewServerCorrectInstance();
		String newServerName = "newName";
		serverDAO.save(startServerInstance);
		
		serverService.editServer(startServerInstance.getId(), newServerName);
		
		Server result = serverDAO.get(startServerInstance.getId());
		Assert.assertEquals(newServerName, result.getName());
	}
	
	@Transactional
	@Test(expected = NoServerOfIdInDatabaseException.class)
	public void testEditServerNotExists() {
		Long notExistingServerId = 1L;
		String newServerName = "anyName";
		
		serverService.editServer(notExistingServerId, newServerName);
	}
	
	@Transactional
	@Test
	public void testListServersEmptyList() {
		List<Server> result = serverService.listServers();
		Assert.assertTrue(result.isEmpty());
	}
	
	@Transactional
	@Test
	public void testListServers() {
		Server serverInDatabase = createNewServerCorrectInstance();
		serverDAO.save(serverInDatabase);
		
		List<Server> result = serverService.listServers();
		Assert.assertEquals(serverInDatabase, result.get(0));
	}
	
	@Transactional
	@Test
	public void testCountServers() {
		Server serverInDatabase = createNewServerCorrectInstance();
		serverDAO.save(serverInDatabase);
		
		int result = serverService.countServers();
		Assert.assertEquals(1, result);
	}
	
	@Transactional
	@Test
	public void testCountServersNoServers() {
		int result = serverService.countServers();
		int expected = 0;
		Assert.assertEquals(expected, result);
	}
	
	private void assertServerWasPersisted(Server expected) {
		Server result = (Server) getCurrentHibernateSession().get(Server.class, expected.getId());
		Assert.assertNotNull(result);
		Assert.assertEquals(expected.getId(), result.getId());
		Assert.assertEquals(expected.getName(), result.getName());
	}
}
