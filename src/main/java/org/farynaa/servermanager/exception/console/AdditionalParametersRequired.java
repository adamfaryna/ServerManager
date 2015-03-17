package org.farynaa.servermanager.exception.console;

import org.farynaa.servermanager.ConsoleConstans;

/**
 * @author devil
 *
 */
public class AdditionalParametersRequired extends RuntimeException {

	private static final long serialVersionUID = -5017681720867777688L;
	
	private static final String MESSAGE = "error: Additional parameters is required for this command.\n"
			+ ConsoleConstans.STANDARD_CONSOLE_USAGE_ADVICE;
	
	public AdditionalParametersRequired() {
		super(MESSAGE);
	}
}
