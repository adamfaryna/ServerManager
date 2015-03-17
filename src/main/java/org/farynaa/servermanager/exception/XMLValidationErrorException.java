package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class XMLValidationErrorException extends RuntimeException {

	private static final long serialVersionUID = 4882238774555754400L;
	
	private static final String MESSAGE = "error: Passed XML document is invalid XML file."; 
	
	public XMLValidationErrorException() {
		super(MESSAGE);
	}
}
