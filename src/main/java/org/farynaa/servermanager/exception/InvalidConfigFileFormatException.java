package org.farynaa.servermanager.exception;

/**
 * @author devil
 *
 */
public class InvalidConfigFileFormatException extends RuntimeException {

	private static final String MESSAGE = "error: Invalid configuration file format.\n"
			+ "Correct format is example: (from default configuration)\n"
			+ "database.filename=dbhome.db\n"
			+ "database.name=database\n"
			+ "database.user=dbuser\n"
			+ "database.password=dbpassword\n"
			+ "\n\n"
			+ "Use 'manager help' to print information how to use program.\n";
	
	public InvalidConfigFileFormatException() {
		super(MESSAGE);
	}
	
}
