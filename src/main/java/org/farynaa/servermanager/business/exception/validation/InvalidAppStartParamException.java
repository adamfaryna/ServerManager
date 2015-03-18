package org.farynaa.servermanager.business.exception.validation;

import org.farynaa.servermanager.AppConstans;

/**
 * @author devil
 *
 */
public class InvalidAppStartParamException extends RuntimeException {

	private static final long serialVersionUID = -8747398939427339939L;
	private static final String MESSAGE_TEMPLATE = "error: Invalid start param '%s'.\n\n" + AppConstans.HELP_ADVICE_TEXT + "\n";

	public InvalidAppStartParamException(String param) {
		super(String.format(MESSAGE_TEMPLATE, param));
	}	
}
