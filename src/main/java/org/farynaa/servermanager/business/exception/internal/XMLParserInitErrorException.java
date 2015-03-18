package org.farynaa.servermanager.business.exception.internal;

/**
 * @author devil
 *
 */
public class XMLParserInitErrorException extends AbstractInternalErrorException {

	private static final long serialVersionUID = 1490741305655372573L;
	
	private static final String MESSAGE = "internal error: XML parser initialisation error.";

	public XMLParserInitErrorException(Exception exception) {
		super(MESSAGE, exception);
	}
}
