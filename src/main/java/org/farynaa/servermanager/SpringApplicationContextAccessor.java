package org.farynaa.servermanager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring context accessor.
 * 
 * @author adamfaryna@gmail.com
 */
public class SpringApplicationContextAccessor implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		CONTEXT = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return CONTEXT.getBean(beanClass);
	}	
}
