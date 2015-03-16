package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class TooManyStartParamsPassedException extends RuntimeException {

	private static final long serialVersionUID = -983807285888703573L;
	
	private static final String MESSAGE = "error: Too many parameters passed. Use 'manager help' to print information how to use program.";
	
	public TooManyStartParamsPassedException() {
		super(MESSAGE);
	}
}
