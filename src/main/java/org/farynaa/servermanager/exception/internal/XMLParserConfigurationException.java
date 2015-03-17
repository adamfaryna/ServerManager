package org.farynaa.servermanager.exception.internal;

/**
 * @author devil
 *
 */
public class XMLParserConfigurationException extends AbstractInternalError {

	private static final long serialVersionUID = 1490741305655372573L;
	
	private static final String MESSAGE = "internal error: XML parser initialisation error.";

	public XMLParserConfigurationException(Exception exception) {
		super(MESSAGE, exception);
	}
}
