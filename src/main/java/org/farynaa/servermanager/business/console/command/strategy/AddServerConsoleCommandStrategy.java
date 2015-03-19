package org.farynaa.servermanager.business.console.command.strategy;

import java.io.File;

import org.farynaa.servermanager.business.exception.validation.console.AdditionalParametersRequired;
import org.farynaa.servermanager.business.exception.validation.console.PassedServerFileParameterNotExists;
import org.farynaa.servermanager.business.exception.validation.console.TooManyParametersPassedException;

/**
 * @author devil
 *
 */
public class AddServerConsoleCommandStrategy extends AbstractConsoleCommandStrategy {
	
	@Override
	public void process(String[] params) {
		validateAddServerCommandParams(params);
		
		String serverSpecFilename = params[0];
		getServerService().addServer(serverSpecFilename);
		printAddServerCommandSuccessMessage();
	}
	
	private void validateAddServerCommandParams(String[] commandParameters) {
		if (commandParameters.length == 0) {
			throw new AdditionalParametersRequired();
		}
		
		if (commandParameters.length > 1) {
			throw new TooManyParametersPassedException();
		}
		
		String serverFilenameCandidate = commandParameters[0];
		File serverFileCandidate = new File(serverFilenameCandidate);
		if (!serverFileCandidate.exists()) {
			throw new PassedServerFileParameterNotExists();
		}
	}
	
	private void printAddServerCommandSuccessMessage() {
		System.out.println("Server was persisted successfully.");
	}
}
