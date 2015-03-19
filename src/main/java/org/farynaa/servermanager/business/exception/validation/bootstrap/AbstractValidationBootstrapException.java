package org.farynaa.servermanager.business.exception.validation.bootstrap;

/**
 * Abstract {@link RuntimeException} type for bootstrap phase validation errors.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class AbstractValidationBootstrapException extends RuntimeException {

	private static final long serialVersionUID = -3187554771775040304L;
	
	protected static final String STANDARD_APP_USAGE_ADVICE = "Use 'manager help' to print information how to use program.";
	
	protected AbstractValidationBootstrapException(String message) {
		super(message);
	}
}
