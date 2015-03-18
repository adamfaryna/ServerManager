package org.farynaa.servermanager.business.exception.validation.console;


/**
 * @author devil
 *
 */
public class AdditionalParametersRequired extends AbstractConsoleException {

	private static final long serialVersionUID = -5017681720867777688L;
	
	private static final String MESSAGE = "error: Additional parameters is required for this command.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public AdditionalParametersRequired() {
		super(MESSAGE);
	}
}
