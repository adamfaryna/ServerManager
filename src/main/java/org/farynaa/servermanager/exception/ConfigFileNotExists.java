package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class ConfigFileNotExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 888448277206071773L;
	private static final String MESSAGE_TEMPLATE = "error: Configuration file '%s' doesn't exists.\n\n"
			+ "Use 'manager help' to print information how to use program.\n";
	
	public ConfigFileNotExists(String configFilename) {
		super(String.format(MESSAGE_TEMPLATE, configFilename));
	}
}
