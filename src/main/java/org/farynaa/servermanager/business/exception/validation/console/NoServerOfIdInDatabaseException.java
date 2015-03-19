package org.farynaa.servermanager.business.exception.validation.console;


/**
 * Console command validation error thrown when user try to pass id of not persisted server in console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class NoServerOfIdInDatabaseException extends AbstractValidationConsoleException {

	private static final long serialVersionUID = 5227805660923683133L;
	
	private static final String MESSAGE_TEMPLATE = "error: No server of id '%s' in database.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public NoServerOfIdInDatabaseException(Long id) {
		super(String.format(MESSAGE_TEMPLATE, id));
	}
}
