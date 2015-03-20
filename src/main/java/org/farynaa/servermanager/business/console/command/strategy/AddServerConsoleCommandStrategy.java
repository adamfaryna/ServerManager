package org.farynaa.servermanager.business.console.command.strategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.farynaa.servermanager.business.exception.validation.console.AdditionalParametersRequired;
import org.farynaa.servermanager.business.exception.validation.console.PassedServerFileParameterNotExists;
import org.farynaa.servermanager.business.exception.validation.console.TooManyParametersPassedException;

/**
 * Implementation of {@link ConsoleCommandStrategy} for 'addServer' console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class AddServerConsoleCommandStrategy extends AbstractConsoleCommandStrategy {
	
	private static final String SUCCESS_MESSAGE = "Server was persisted successfully.";

	@Override
	public void process(String[] params) {
		validateAddServerCommandParams(params);
		
		String serverFilenameCandidate = params[0];
		InputStream serverSpecFile = getSpecFileAsAInputStream(serverFilenameCandidate);
		validateServerSpecFileExists(serverSpecFile);
		
		getServerService().addServer(serverSpecFile);
		printAddServerCommandSuccessMessage();
	}
	
	private void validateAddServerCommandParams(String[] commandParameters) {
		if (commandParameters.length == 0) {
			throw new AdditionalParametersRequired();
		}
		
		if (commandParameters.length > 1) {
			throw new TooManyParametersPassedException();
		}
	}

	private void validateServerSpecFileExists(InputStream serverSpecFile) {
		if (serverSpecFile == null) {
			throw new PassedServerFileParameterNotExists();
		}
	}

	private InputStream getSpecFileAsAInputStream(String serverSpecFileName) {
		try {
			return new FileInputStream(serverSpecFileName);
			
		} catch (FileNotFoundException e) {
			return getSpecFileFromResources(serverSpecFileName);
		}
	}
	
	private InputStream getSpecFileFromResources(String serverSpecFileName) {
		return AddServerConsoleCommandStrategy.class.getClassLoader().getResourceAsStream(serverSpecFileName);
	}

	private void printAddServerCommandSuccessMessage() {
		System.out.println(SUCCESS_MESSAGE);
	}
}
