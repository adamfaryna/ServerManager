package org.farynaa.servermanager.business.exception.internal;

/**
 * @author adamfaryna@gmail.com
 *
 */
public class XMLParserInitErrorException extends AbstractInternalErrorException {

	private static final long serialVersionUID = 1490741305655372573L;
	
	private static final String MESSAGE_TEMPLATE = "internal error: XML parser initialisation error. %s";

	public XMLParserInitErrorException(Exception exception) {
		super(String.format(MESSAGE_TEMPLATE, exception.getMessage()));
	}
}
