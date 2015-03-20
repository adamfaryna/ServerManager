package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.AbstractTestWithXML;
import org.farynaa.servermanager.business.exception.validation.console.AdditionalParametersRequired;
import org.farynaa.servermanager.business.exception.validation.console.PassedServerFileParameterNotExists;
import org.farynaa.servermanager.business.exception.validation.console.TooManyParametersPassedException;
import org.farynaa.servermanager.business.service.ServerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Tests for {@link AddServerConsoleCommandStrategy}
 * 
 * @author adamfaryna@gmail.com
 */
public class AddServerConsoleCommandStrategyTest extends AbstractTestWithXML {

	private static final String NOT_EXISTING_SPEC_FILENAME = "alamakota.xml";
	
	@InjectMocks
	private AddServerConsoleCommandStrategy strategy;
	
	@Mock
	private ServerService serverServiceMock;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test(expected = AdditionalParametersRequired.class)
	public void testProcessNoCommandParams() {
		strategy.process(new String[]{});
	}
	
	@Test(expected = TooManyParametersPassedException.class)
	public void testProcessMoreThenOneParameterPassed() {
		strategy.process(new String[]{"one", "two"});
	}
	
	@Test(expected = PassedServerFileParameterNotExists.class)
	public void testPassedNotExistingSpecFilename() {
		strategy.process(new String[]{NOT_EXISTING_SPEC_FILENAME});
	}
	
	@Test
	public void testProcessWithCorrectParam() {	
		strategy.process(new String[]{CORRECT_SERVER_SPEC_FILENAME});
	}
	

}
