package org.farynaa.servermanager.business.console.command.strategy;

import org.farynaa.servermanager.SpringApplicationContextAccessor;
import org.farynaa.servermanager.business.exception.validation.console.InvalidParameterException;
import org.farynaa.servermanager.business.service.ServerService;

/**
 * Abstract implementation of {@link ConsoleCommandStrategy}.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class AbstractConsoleCommandStrategy implements ConsoleCommandStrategy {

	@Override
	public boolean isKeepConsoleRunning() {
		return true;
	}

	protected ServerService getServerService() {
		return SpringApplicationContextAccessor.getBean(ServerService.class);
	}

	protected void validateCorrectServerId(String serverIdCandidateString) {
		try {
			Long.valueOf(serverIdCandidateString);

		} catch (NumberFormatException e) {
			throw new InvalidParameterException();
		}
	}
}
