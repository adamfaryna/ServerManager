package org.farynaa.servermanager.business.exception.internal;

/**
 * @author devil
 *
 */
public class InvalidSchemaFileErrorException extends AbstractInternalErrorException {

	private static final long serialVersionUID = 3259228828135630770L;
	
	private static final String MESSAGE = "internal error: Internal Schema file is corrupted.";
	
	public InvalidSchemaFileErrorException() {
		super(MESSAGE);
	}
}
