package org.farynaa.servermanager.business.exception.validation.console;


/**
 * Console command validation error thrown when user does not pass all required parameters for selected console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class AdditionalParametersRequired extends AbstractValidationConsoleException {

	private static final long serialVersionUID = -5017681720867777688L;
	
	private static final String MESSAGE = "error: Additional parameters are required for this command.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public AdditionalParametersRequired() {
		super(MESSAGE);
	}
}
