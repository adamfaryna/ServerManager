package org.farynaa.servermanager;

import org.farynaa.servermanager.business.exception.validation.bootstrap.InvalidConfigFilenameSuffixException;
import org.farynaa.servermanager.business.exception.validation.bootstrap.PassedConfigFilenameForNotExistingException;
import org.farynaa.servermanager.test.SpringApplicationContextGetter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;

/**
 * Tests for {@link SpringApplicationContextInitializer}.
 * 
 * @author adamfaryna@gmail.com
 */
public class SpringApplicationContextInitializerTest {

	private static final String TEST_SYSTEM_CONTEXT_XML = "test-application-context.xml";
	private static final String NOT_EXISTING_RESOURCE_NAME = "alaMaKota.properties";
	private static final String INVALID_CONFIG_NAME_SUFFIX = "incorrectPropertiesFilename.prop";
	private static final String CORRECT_USER_PROPERTIES_RESOURCE_NAME = "correctUserProperties.properties";
	
	
	@Test(expected = PassedConfigFilenameForNotExistingException.class)
	public void testInitWithExternalNotExistingConfig() {
		AbstractResource notExistingResourceName = getResource(NOT_EXISTING_RESOURCE_NAME);
		SpringApplicationContextInitializer.initialize(notExistingResourceName);
	}

	@Test(expected = InvalidConfigFilenameSuffixException.class)
	public void testInitWithExternalExistingInvalidSuffixConfig() {
		AbstractResource invalidConfigNameSuffixResource = getResource(INVALID_CONFIG_NAME_SUFFIX);
		SpringApplicationContextInitializer.initialize(invalidConfigNameSuffixResource);
	}
	
	@Test
	public void testInitializeWithExternalConfiguration() {
		AbstractResource invalidConfigNameSuffixResource = getResource(CORRECT_USER_PROPERTIES_RESOURCE_NAME);
		SpringApplicationContextInitializer.initialize(invalidConfigNameSuffixResource, TEST_SYSTEM_CONTEXT_XML);
		checkIsSpringContextIsOK();
	}

	private void checkIsSpringContextIsOK() {
		Assert.assertNotNull(SpringApplicationContextGetter.getCONTEXT());
		checkIfPropertiesMergeCorrect();
	}

	private void checkIfPropertiesMergeCorrect() {
		String propertyValue = SpringApplicationContextGetter.getCONTEXT().getEnvironment().getProperty("database.name");
		Assert.assertEquals("database", propertyValue);
	}

	private AbstractResource getResource(String resourceName) {
		return new ClassPathResource(resourceName);
	}
}
