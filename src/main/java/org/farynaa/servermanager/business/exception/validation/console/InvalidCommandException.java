package org.farynaa.servermanager.business.exception.validation.console;


/**
 * @author devil
 *
 */
public class InvalidCommandException extends AbstractConsoleException {

	private static final long serialVersionUID = -4548094251168693025L;
	
	private static final String MESSAGE_TEMPLATE = "error: Invalid command '%s'.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public InvalidCommandException(String command) {
		super(String.format(MESSAGE_TEMPLATE, command));
	}
}
