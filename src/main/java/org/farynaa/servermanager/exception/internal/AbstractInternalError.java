package org.farynaa.servermanager.exception.internal;

/**
 * @author devil
 *
 */
public abstract class AbstractInternalError extends RuntimeException {

	private static final long serialVersionUID = -9116801148835741860L;
	
	protected AbstractInternalError(String message, Exception exception) {
		super(message, exception);
	}
}
