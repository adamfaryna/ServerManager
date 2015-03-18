package org.farynaa.servermanager.business.exception.internal;

/**
 * @author devil
 *
 */
public class XMLParseErrorException extends AbstractInternalErrorException {

	private static final long serialVersionUID = -4901888079413561743L;
	
	private static final String MESSAGE = "internal error: XML parse error.";
	
	public XMLParseErrorException(Exception exception) {
		super(MESSAGE, exception);
	}
}
