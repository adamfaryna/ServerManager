package org.farynaa.servermanager.business.exception.validation.bootstrap;


/**
 * Bootstrap phase exception thrown when user pass config file with invalid format.
 * 
 * @author adamfaryna@gmail.com
 */
public class InvalidConfigFileFormatException extends AbstractValidationBootstrapException {

	private static final long serialVersionUID = -3308183275575627851L;
	
	private static final String MESSAGE = "error: Invalid configuration file format.\n"
			+ "Correct format is example: (from default configuration)\n"
			+ "database.filename=dbhome.db\n"
			+ "database.name=database\n"
			+ "database.user=dbuser\n"
			+ "database.password=dbpassword\n"
			+ "\n\n"
			+ STANDARD_APP_USAGE_ADVICE;
	
	public InvalidConfigFileFormatException() {
		super(MESSAGE);
	}	
}
