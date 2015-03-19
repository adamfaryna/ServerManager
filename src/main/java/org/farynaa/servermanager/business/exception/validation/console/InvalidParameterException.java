package org.farynaa.servermanager.business.exception.validation.console;


/**
 * Console command validation error thrown when user try to pass invalid parameter to console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class InvalidParameterException extends AbstractValidationConsoleException {

	private static final long serialVersionUID = -7267217789098931032L;
	
	private static final String MESSAGE = "error: Invalid parameter.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public InvalidParameterException() {
		super(MESSAGE);
	}
}
