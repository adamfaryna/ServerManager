package org.farynaa.servermanager.business.exception.validation.console;


/**
 * Console command validation error thrown when user pass too many parameters for console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class TooManyParametersPassedException extends AbstractValidationConsoleException {

	private static final long serialVersionUID = -5017681720867777688L;
	
	private static final String MESSAGE = "error: Too many parameteres were passed to this command.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public TooManyParametersPassedException() {
		super(MESSAGE);
	}
}
