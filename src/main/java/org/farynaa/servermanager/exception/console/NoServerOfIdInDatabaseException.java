package org.farynaa.servermanager.exception.console;

import org.farynaa.servermanager.ConsoleConstans;

/**
 * @author devil
 *
 */
public class NoServerOfIdInDatabaseException extends RuntimeException {

	private static final long serialVersionUID = 5227805660923683133L;
	
	private static final String MESSAGE_TEMPLATE = "error: No server of id '%s' in database.\n"
			+ ConsoleConstans.STANDARD_CONSOLE_USAGE_ADVICE;
	
	public NoServerOfIdInDatabaseException(Long id) {
		super(String.format(MESSAGE_TEMPLATE, id));
	}
}
