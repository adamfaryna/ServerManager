package org.farynaa.servermanager.business.exception.validation.bootstrap;


/**
 * Bootstrap phase exception thrown when selected user config file does not exists.
 * 
 * @author adamfaryna@gmail.com
 * 
 */
public class PassedConfigFilenameForNotExistingException extends AbstractValidationBootstrapException {

	private static final long serialVersionUID = 888448277206071773L;
	
	private static final String MESSAGE_TEMPLATE = "error: Configuration file '%s' doesn't exists. " + STANDARD_APP_USAGE_ADVICE;
	
	public PassedConfigFilenameForNotExistingException(String configFilename) {
		super(String.format(MESSAGE_TEMPLATE, configFilename));
	}
}
