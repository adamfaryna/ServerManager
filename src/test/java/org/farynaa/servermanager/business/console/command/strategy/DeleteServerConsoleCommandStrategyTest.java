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
 * Tests for {@link DeleteServerConsoleCommandStrategy}.
 * 
 * @author adamfaryna@gmail.com
 */
public class DeleteServerConsoleCommandStrategyTest {

	@InjectMocks
	private DeleteServerConsoleCommandStrategy strategy;
	
	@Mock
	private ServerService serverService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = AdditionalParametersRequired.class)
	public void testProcessWithoutParameters() {
		strategy.process(new String[]{});
	}
	
	@Test(expected = TooManyParametersPassedException.class)
	public void testProcessWithTooManyParams() {
		strategy.process(new String[]{"1", "2"});
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testProcessWithNotNumberParam() {
		strategy.process(new String[]{"ala"});
	}
	
	@Test
	public void testProcessWithParams() {
		Long serverId = 1L;
		strategy.process(new String[]{serverId.toString()});
		Mockito.verify(serverService, Mockito.times(1)).deleteServer(serverId);
	}
}
