package org.farynaa.servermanager.business.exception.validation.bootstrap;


/**
 * Bootstrap phase exception thrown when user pass too many parameters when running an application.
 * 
 * @author adamfaryna@gmail.com
 */
public class TooManyStartParamsPassedException extends AbstractValidationBootstrapException {

	private static final long serialVersionUID = -983807285888703573L;
	
	private static final String MESSAGE = "error: Too many parameters passed. " + STANDARD_APP_USAGE_ADVICE;
	
	public TooManyStartParamsPassedException() {
		super(MESSAGE);
	}
}
