package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class InvalidSchemaFileException extends RuntimeException {

	private static final long serialVersionUID = 3259228828135630770L;
	
	private static final String MESSAGE = "internal error: Internal Schema file is corrupted. Download this application once again";
	
	public InvalidSchemaFileException() {
		super(MESSAGE);
	}
}
