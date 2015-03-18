package org.farynaa.servermanager.business.exception.validation.console;


/**
 * @author devil
 *
 */
public class PassedServerFileParameterNotExists extends AbstractConsoleException {

	private static final long serialVersionUID = 8074285769453347318L;
	
	private static final String MESSAGE = "error: Passed server file name doesn't exists.\n" + STANDARD_CONSOLE_USAGE_ADVICE;
	
	public PassedServerFileParameterNotExists() {
		super(MESSAGE);
	}
}
