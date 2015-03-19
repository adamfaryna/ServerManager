package org.farynaa.servermanager.business.exception.internal;

/**
 * Abstract {@link RuntimeException} type for internal errors.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class AbstractInternalErrorException extends RuntimeException {

	private static final long serialVersionUID = -9116801148835741860L;
	
	protected AbstractInternalErrorException(String message) {
		super(message);
	}
}
