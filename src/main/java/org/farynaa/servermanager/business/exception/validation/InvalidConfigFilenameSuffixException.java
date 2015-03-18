package org.farynaa.servermanager.business.exception.validation;

import org.farynaa.servermanager.AppConstans;

/**
 * @author devil
 *
 */
public class InvalidConfigFilenameSuffixException extends RuntimeException {

	private static final long serialVersionUID = 1235849757879448745L;

	private static final String MESSAGE = "error: Incorrect configuration file name suffix. Correct suffix is '.properties'. "
			+ AppConstans.HELP_ADVICE_TEXT + "\n";

	public InvalidConfigFilenameSuffixException() {
		super(MESSAGE);
	}
}
