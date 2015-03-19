package org.farynaa.servermanager.business.exception.validation.console;


/**
 * Console command validation error thrown when user pass server spec filename that not exists.
 * 
 * @author adamfaryna@gmail.com
 */
public class PassedServerFileParameterNotExists extends AbstractValidationConsoleException {

	private static final long serialVersionUID = 8074285769453347318L;
	
	private static final String MESSAGE = "error: Passed server file name doesn't exists.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public PassedServerFileParameterNotExists() {
		super(MESSAGE);
	}
}
