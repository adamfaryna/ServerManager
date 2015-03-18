package org.farynaa.servermanager.business.exception.validation.console;


/**
 * @author devil
 *
 */
public class InvalidParameterException extends AbstractConsoleException {

	private static final long serialVersionUID = -7267217789098931032L;
	
	private static final String MESSAGE = "error: Invalid parameter.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public InvalidParameterException() {
		super(MESSAGE);
	}
}
