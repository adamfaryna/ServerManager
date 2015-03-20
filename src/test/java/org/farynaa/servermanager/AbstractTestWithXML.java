package org.farynaa.servermanager;

import java.io.InputStream;

import org.farynaa.servermanager.business.service.impl.ServerServiceImplIntegrationTest;

/**
 * Abstract implementation of test that uses common XML files.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class AbstractTestWithXML {

	protected static final String CORRECT_SERVER_SPEC_FILENAME = "correctServerSpecFile.xml";
	protected static final String CORRUPTED_SERVER_SPEC_FILENAME = "corruptedServerSpecFile.xml";
	

	protected InputStream getServerSpecFileAsInputStream(String resourceName) {
		InputStream inputStream = ServerServiceImplIntegrationTest.class.getClassLoader().getResourceAsStream(resourceName);
		return inputStream;
	}
	
	protected InputStream getCorrectServerSpecFileAsInputStream() {
		return getServerSpecFileAsInputStream(CORRECT_SERVER_SPEC_FILENAME);
	}

	protected InputStream getCorruptedServerSpecFileAsInputStream() {
		return getServerSpecFileAsInputStream(CORRUPTED_SERVER_SPEC_FILENAME);
	}
}
