package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.business.service.ServerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Tests for {@link ListServersConsoleCommandStrategy}.
 * 
 * @author adamfaryna@gmail.com
 */
public class ListServersConsoleCommandStrategyTest {

	@InjectMocks
	private ListServersConsoleCommandStrategy strategy;
	
	@Mock
	private ServerService serverService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testProcess() {
		strategy.process(new String[]{});
		
		Mockito.verify(serverService, Mockito.times(1)).listServers();
	}
}
