package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class InvalidConfigFilenameSuffix extends RuntimeException {

	private static final long serialVersionUID = 1235849757879448745L;
	private static final String MESSAGE = "error: Incorrect configuration file name suffix. Correct suffix is '.properties'.\n\n"
			+ "Use 'manager help' to print information how to use program.\n";
	
	public InvalidConfigFilenameSuffix() {
		super(MESSAGE);
	}
}
