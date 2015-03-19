package org.farynaa.servermanager.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring context getter.
 * 
 * @author adamfaryna@gmail.com
 */
public class SpringApplicationContextGetter implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		CONTEXT = applicationContext;
	}

	public static ApplicationContext getCONTEXT() {
		return CONTEXT;
	}
	
	private SpringApplicationContextGetter() {
		// do nothing
	}
}
