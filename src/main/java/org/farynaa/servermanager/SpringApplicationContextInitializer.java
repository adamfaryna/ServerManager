package org.farynaa.servermanager;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.farynaa.servermanager.business.exception.validation.bootstrap.ConfigFileNotExistsException;
import org.farynaa.servermanager.business.exception.validation.bootstrap.InvalidConfigFilenameSuffixException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Spring Application context initializer which merges default configuration with user defined configuration if it is present.
 * @author adamfaryna@gmail.com
 */
public class SpringApplicationContextInitializer {

	private static final String PROPERTIES_FILE_SUFFIX = ".properties";
	private static final String SPRING_APPLICATION_CONTEXT_XML_FILENAME = "application-context.xml";
	private static final String DEFAULT_PROPERTIES = "default.properties";

	private String extraParam;
	private Properties defaultProperties;
	private ClassPathXmlApplicationContext applicationContext;
	
	public static void initialize(String extraParam) {
		new SpringApplicationContextInitializer(extraParam).prepareAndInitSpringContext();
	}
	
	private SpringApplicationContextInitializer(String extraParam) {
		this.extraParam = extraParam;
	}
	
	private void prepareAndInitSpringContext() {
		ClassPathResource defaultPropertiesResource = new ClassPathResource(DEFAULT_PROPERTIES);
		defaultProperties = extractPropertiesFromResource(defaultPropertiesResource);

		if (isExternalConfigFilenamePassed()) {
			mergeUserAndDefaultConfig();
		}

		initSpringContext();
	}
	
	private void mergeUserAndDefaultConfig() {
		Properties userProperties = extractUserConfigProperties();
		defaultProperties.putAll(userProperties);
	}
	
	private Properties extractUserConfigProperties() {
		FileSystemResource fileSystemResource = new FileSystemResource(getExtraParam());
		validate(fileSystemResource);
		Properties userProperties = extractPropertiesFromResource(fileSystemResource);
		return userProperties;
	}

	private boolean isExternalConfigFilenamePassed() {
		return extraParam != null && !extraParam.isEmpty();
	}
	
	private void initSpringContext() {
		applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation(SPRING_APPLICATION_CONTEXT_XML_FILENAME);

		updateSpringContextProperties();
		applicationContext.refresh();
	}
	
	private String getExtraParam() {
		return extraParam;
	}
	
	private void updateSpringContextProperties() {
		Map<String, Object> systemProperties = applicationContext.getEnvironment().getSystemProperties();
		for (String prop : defaultProperties.stringPropertyNames()) {
			systemProperties.put(prop, defaultProperties.getProperty(prop));
		}
	}

	private Properties extractPropertiesFromResource(AbstractResource resource) {
		try {
			return PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void validate(FileSystemResource fileSystemResource) {
		if (!fileSystemResource.getFilename().endsWith(PROPERTIES_FILE_SUFFIX)) {
			throw new InvalidConfigFilenameSuffixException();
		}

		if (!fileSystemResource.exists()) {
			throw new ConfigFileNotExistsException(fileSystemResource.getFilename());
		}
	}
}
