package org.farynaa.servermanager.business.exception.internal;

/**
 * @author adamfaryna@gmail.com
 *
 */
public class XMLParseErrorException extends AbstractInternalErrorException {

	private static final long serialVersionUID = -4901888079413561743L;
	
	private static final String MESSAGE_TEMPLATE = "internal error: XML parse error. %s";
	
	public XMLParseErrorException(Exception exception) {
		super(String.format(MESSAGE_TEMPLATE, exception.toString()));
	}
}
