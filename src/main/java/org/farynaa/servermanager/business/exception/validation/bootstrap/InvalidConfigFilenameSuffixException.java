package org.farynaa.servermanager.business.exception.validation.bootstrap;


/**
 * Bootstrap phase exception thrown when user pass config file with invalid format.
 * 
 * @author adamfaryna@gmail.com
 */
public class InvalidConfigFilenameSuffixException extends AbstractValidationBootstrapException {

	private static final long serialVersionUID = 1235849757879448745L;

	private static final String MESSAGE = "error: Incorrect configuration file name suffix. Correct suffix is '.properties'. "
			+ STANDARD_APP_USAGE_ADVICE;

	public InvalidConfigFilenameSuffixException() {
		super(MESSAGE);
	}
}
