package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.business.service.ServerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Test for {@link CountServersConsoleCommandStrategy}.
 * 
 * @author adamfaryna@gmail.com
 */
public class CountServersConsoleCommandStrategyTest {

	
	@InjectMocks
	private CountServersConsoleCommandStrategy strategy;
	
	@Mock
	private ServerService serverServiceMock;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testProcess() {
		strategy.process(new String[]{});
		
		Mockito.verify(serverServiceMock, Mockito.times(1)).countServers();
	}

}
