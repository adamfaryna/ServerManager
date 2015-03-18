package org.farynaa.servermanager.business.exception.validation;

import org.farynaa.servermanager.AppConstans;

/**
 * @author devil
 *
 */
public class InvalidConfigFileFormatException extends RuntimeException {

	private static final long serialVersionUID = -3308183275575627851L;
	
	private static final String MESSAGE = "error: Invalid configuration file format.\n"
			+ "Correct format is example: (from default configuration)\n"
			+ "database.filename=dbhome.db\n"
			+ "database.name=database\n"
			+ "database.user=dbuser\n"
			+ "database.password=dbpassword\n"
			+ "\n\n"
			+ AppConstans.HELP_ADVICE_TEXT + "\n";
	
	public InvalidConfigFileFormatException() {
		super(MESSAGE);
	}	
}
