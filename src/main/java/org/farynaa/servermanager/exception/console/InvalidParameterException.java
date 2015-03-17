package org.farynaa.servermanager.exception.console;

import org.farynaa.servermanager.ConsoleConstans;

/**
 * @author devil
 *
 */
public class InvalidParameterException extends RuntimeException {

	private static final long serialVersionUID = -7267217789098931032L;
	
	private static final String MESSAGE = "error: Invalid parameter.\n" + ConsoleConstans.STANDARD_CONSOLE_USAGE_ADVICE;
	
	public InvalidParameterException() {
		super(MESSAGE);
	}
}
