package org.farynaa.servermanager.business.exception.validation;

import org.farynaa.servermanager.AppConstans;

/**
 * @author devil
 *
 */
public class ConfigFileNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 888448277206071773L;
	
	private static final String MESSAGE_TEMPLATE = "error: Configuration file '%s' doesn't exists. " + AppConstans.HELP_ADVICE_TEXT + "\n";
	
	public ConfigFileNotExistsException(String configFilename) {
		super(String.format(MESSAGE_TEMPLATE, configFilename));
	}
}
