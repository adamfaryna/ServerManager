package org.farynaa.servermanager.business.exception.internal;

/**
 * @author devil
 *
 */
public abstract class AbstractInternalErrorException extends RuntimeException {

	private static final long serialVersionUID = -9116801148835741860L;
	
	protected AbstractInternalErrorException(String message, Exception exception) {
		super(message, exception);
	}
}
