package org.farynaa.servermanager;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.farynaa.servermanager.business.exception.validation.bootstrap.PassedConfigFilenameForNotExistingException;
import org.farynaa.servermanager.business.exception.validation.bootstrap.InvalidConfigFilenameSuffixException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Spring Application context initializer which merges default configuration
 * with user defined configuration if it is present.
 * 
 * @author adamfaryna@gmail.com
 */
public class SpringApplicationContextInitializer {

	private static final String PROPERTIES_FILE_SUFFIX = ".properties";
	private static final String SPRING_APPLICATION_CONTEXT_XML_FILENAME = "application-context.xml";
	private static final String DEFAULT_PROPERTIES = "default.properties";

	private AbstractResource externalConfigFileResource;
	private Properties defaultProperties;
	private ClassPathXmlApplicationContext applicationContext;
	private String additionalSpringConfigLocation;

	public static void initialize(AbstractResource externalConfigResource) {
		initialize(externalConfigResource, null);
	}
	
	public static void initialize(AbstractResource externalConfigResource, String additionalSpringConfigFile) {
		new SpringApplicationContextInitializer(externalConfigResource, additionalSpringConfigFile).prepareAndInitSpringContext();
	}

	private SpringApplicationContextInitializer(
			AbstractResource externalConfigResource,
			String additionalSpringConfigFile) {
		this.externalConfigFileResource = externalConfigResource;
		this.additionalSpringConfigLocation = additionalSpringConfigFile;
	}

	private void prepareAndInitSpringContext() {
		ClassPathResource defaultPropertiesResource = new ClassPathResource(
				DEFAULT_PROPERTIES);
		defaultProperties = extractPropertiesFromResource(defaultPropertiesResource);

		if (isExternalConfigPassed()) {
			mergeUserAndDefaultConfig();
		}

		initSpringContext();
	}

	private void mergeUserAndDefaultConfig() {
		Properties userProperties = extractUserConfigProperties();
		defaultProperties.putAll(userProperties);
	}

	private Properties extractUserConfigProperties() {
		validateExternalConfigFileResource();
		Properties userProperties = extractPropertiesFromResource(externalConfigFileResource);
		return userProperties;
	}

	private boolean isExternalConfigPassed() {
		return externalConfigFileResource != null;
	}

	private void initSpringContext() {
		applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation(getConfigLocations());
		updateSpringContextProperties();
		applicationContext.refresh();
	}
	
	private String getConfigLocations() {
		if (additionalSpringConfigLocation != null && !additionalSpringConfigLocation.isEmpty() ) {
			return SPRING_APPLICATION_CONTEXT_XML_FILENAME + "," + additionalSpringConfigLocation;
		} else {
			return SPRING_APPLICATION_CONTEXT_XML_FILENAME;
		}
	}

	private void updateSpringContextProperties() {
		Map<String, Object> systemProperties = applicationContext
				.getEnvironment().getSystemProperties();
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

	private void validateExternalConfigFileResource() {
		if (!externalConfigFileResource.exists()) {
			throw new PassedConfigFilenameForNotExistingException(externalConfigFileResource.getFilename());
		}
		if (!externalConfigFileResource.getFilename().endsWith(PROPERTIES_FILE_SUFFIX)) {
			throw new InvalidConfigFilenameSuffixException();
		}
	}
}
