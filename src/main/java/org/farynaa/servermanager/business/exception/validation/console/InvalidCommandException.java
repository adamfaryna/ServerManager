package org.farynaa.servermanager.business.exception.validation.console;


/**
 * Console command validation error thrown when user try to invoke not supported command.
 * 
 * @author adamfaryna@gmail.com
 */
public class InvalidCommandException extends AbstractValidationConsoleException {

	private static final long serialVersionUID = -4548094251168693025L;
	
	private static final String MESSAGE_TEMPLATE = "error: Invalid command '%s'.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public InvalidCommandException(String command) {
		super(String.format(MESSAGE_TEMPLATE, command));
	}
}
