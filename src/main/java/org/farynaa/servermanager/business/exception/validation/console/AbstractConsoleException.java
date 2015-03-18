package org.farynaa.servermanager.business.exception.validation.console;

/**
 * @author devil
 *
 */
public abstract class AbstractConsoleException extends RuntimeException {

	private static final long serialVersionUID = 465289929881224895L;

	protected static final String STANDARD_CONSOLE_USAGE_ADVICE = "Use 'help' to print information how to use console.\n";
	
	protected AbstractConsoleException(String message) {
		super(message);
	}
}
