package org.farynaa.servermanager.business.exception.validation.console;

/**
 * Abstract {@link RuntimeException} type for console commands validation errors.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class AbstractValidationConsoleException extends RuntimeException {

	private static final long serialVersionUID = 465289929881224895L;

	protected static final String STANDARD_CONSOLE_USAGE_ADVICE = "Use 'help' to print information how to use console.\n";
	
	protected AbstractValidationConsoleException(String message) {
		super(message);
	}
}
