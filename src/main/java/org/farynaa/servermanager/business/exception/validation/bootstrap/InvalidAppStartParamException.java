package org.farynaa.servermanager.business.exception.validation.bootstrap;


/**
 * Bootstrap phase exception thrown when user pass invalid application start param.
 * 
 * @author adamfaryna@gmail.com
 */
public class InvalidAppStartParamException extends AbstractValidationBootstrapException {

	private static final long serialVersionUID = -8747398939427339939L;
	
	private static final String MESSAGE_TEMPLATE = "error: Invalid start param '%s'. " + STANDARD_APP_USAGE_ADVICE;

	public InvalidAppStartParamException(String param) {
		super(String.format(MESSAGE_TEMPLATE, param));
	}	
}
