package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.business.exception.validation.console.AdditionalParametersRequired;
import org.farynaa.servermanager.business.exception.validation.console.TooManyParametersPassedException;

/**
 * Implementation of {@link ConsoleCommandStrategy} for 'deleteServer' console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class DeleteServerConsoleCommandStrategy extends AbstractConsoleCommandStrategy {

	@Override
	public void process(String[] params) {
		validateDeleteServerCommandParams(params);

		String serverIdString = params[0];
		Long serverId = Long.valueOf(serverIdString);

		getServerService().deleteServer(serverId);
		printDeleteServerCommandSuccessMessage(serverId);
	}
	
	private void validateDeleteServerCommandParams(String[] params) {
		if (params.length == 0) {
			throw new AdditionalParametersRequired();
		}
		
		if (params.length > 1) {
			throw new TooManyParametersPassedException();
		}

		String serverIdCandidateString = params[0];
		validateCorrectServerId(serverIdCandidateString);
	}
	
	private void printDeleteServerCommandSuccessMessage(Long serverId) {
		String message = String.format("Server with id '%s' has been deleted.", serverId);
		System.out.println(message);
	}
}
