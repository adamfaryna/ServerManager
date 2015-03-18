package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.business.exception.validation.console.AdditionalParametersRequired;

/**
 * @author devil
 *
 */
public class EditServerConsoleCommandStrategy extends AbstractConsoleCommandStrategy {
	@Override
	public void process(String[] params) {
		validateEditServerCommandParams(params);

		String serverIdString = params[0];
		Long serverId = Long.valueOf(serverIdString);
		String newServerName = params[1];

		getServerService().editServer(serverId, newServerName);
		printEditServerCommandSuccessMessage(serverId, newServerName);
	}
	
	private void validateEditServerCommandParams(String[] commandParameters) {
		if (commandParameters.length != 2) {
			throw new AdditionalParametersRequired();
		}

		String serverIdCandidateString = commandParameters[0];
		validateCorrectServerId(serverIdCandidateString);
	}
	
	private void printEditServerCommandSuccessMessage(Long serverId,
			String newServerName) {
		String message = String.format(
				"Name of the server with id '%d' has been changed to '%s'.",
				serverId, newServerName);
		System.out.println(message);
	}

}
