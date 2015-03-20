package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.business.exception.validation.console.AdditionalParametersRequired;
import org.farynaa.servermanager.business.exception.validation.console.InvalidParameterException;
import org.farynaa.servermanager.business.exception.validation.console.TooManyParametersPassedException;
import org.farynaa.servermanager.business.service.ServerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Tests for {@link EditServerConsoleCommandStrategy}.
 * 
 * @author adamfaryna@gmail.com
 */
public class EditServerConsoleCommandStrategyTest {

	@InjectMocks
	private EditServerConsoleCommandStrategy strategy;
	
	@Mock
	private ServerService serverService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = AdditionalParametersRequired.class)
	public void testProcessWithInsufficientParams() {
		strategy.process(new String[]{"1"});
	}
	
	@Test(expected = TooManyParametersPassedException.class)
	public void testProcessWithTooManyParams() {
		strategy.process(new String[]{"1", "ala", "ma", "kota"});
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testProcessWithNotNumberFirstParam() {
		strategy.process(new String[]{"ala", "kot"});
	}
	
	@Test
	public void testProcessWithParams() {
		Long serverId = 1L;
		String newServerName = "newName";
		
		strategy.process(new String[]{serverId.toString(), newServerName});
		Mockito.verify(serverService, Mockito.times(1)).editServer(serverId, newServerName);
	}

}
