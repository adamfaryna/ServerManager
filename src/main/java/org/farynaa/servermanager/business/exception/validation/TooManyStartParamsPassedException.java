package org.farynaa.servermanager.business.exception.validation;

import org.farynaa.servermanager.AppConstans;

/**
 * @author devil
 *
 */
public class TooManyStartParamsPassedException extends RuntimeException {

	private static final long serialVersionUID = -983807285888703573L;
	
	private static final String MESSAGE = "error: Too many parameters passed. " + AppConstans.HELP_ADVICE_TEXT + "\n";
	
	public TooManyStartParamsPassedException() {
		super(MESSAGE);
	}
}
