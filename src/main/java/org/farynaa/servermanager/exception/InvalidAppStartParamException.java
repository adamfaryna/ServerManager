package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class InvalidAppStartParamException extends RuntimeException {

	private static final long serialVersionUID = -8747398939427339939L;
	private static final String MESSAGE_TEMPLATE = "error: Invalid start param '%s'.\n\n" +
			"Use 'manager help' to print information how to use program.\n";

	public InvalidAppStartParamException(String param) {
		super(String.format(MESSAGE_TEMPLATE, param));
	}
	
}
