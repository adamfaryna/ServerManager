package org.farynaa.servermanager;

import java.io.IOException;
import java.util.Properties;

import org.farynaa.servermanager.exception.InvalidAppStartParam;
import org.farynaa.servermanager.exception.InvalidConfigFileFormat;
import org.farynaa.servermanager.exception.InvalidConfigFilenameSuffix;
import org.farynaa.servermanager.exception.TooManyStartParamsPassedException;
import org.farynaa.servermanager.exception.ConfigFileNotExists;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author devil
 *
 */
public class StartParametersProcessor {

	private static final String HELP_APP_PARAM_NAME = "help";
	private static final String CONFIG_FILE_CORRECT_SUFFIX = ".properties";
	private static final String DEFAULT_PROPERTIES_FILENAME = "default.properties";

	private String[] args;
	private FileSystemResource userConfigFile;
	private AbstractResource currentConfigFileResource;
	private boolean runParamsProcessed = false;
	private boolean helpParamPresent;
	private boolean isUserProvideConfigFilename;

//	public static StartParametersProcessor create(String[] args) {
//		return new StartParametersProcessor(args);
//	}
	
//	public AbstractResource getApplicationPropertiesResource() {
//		if (!runParamsProcessed) {
//			process();
//		}
//		
//		return currentConfigFileResource;
//	}
//
//	private void process() {
//		if (runParamsProcessed) {
//			return;
//		}
//		
//		if (isRunParamsPresent()) {
//			validateRunParam();
//			processRunParams();
//		}
//		
//	}
//
//	private boolean isRunParamsPresent() {
//		return args.length == 1;
//	}
//	
//	private void processRunParams() {
//		if (isParamPrintHelp()) {
//			helpParamPresent = true;
//			return;
//		}
//		
//		processUserConfigParam();
//	}
//	
//	private void processUserConfigParam() {
//		validateUserConfigFileParam();
//		
//		if (isUserProvideConfigFilename()) {
//			setUserConfigAsCurrentConfig();
//
//		} else {
//			setDefaultConfigAsCurrentConfig();
//		}
//	}
//
//	private StartParametersProcessor(String[] args) {
//		this.args = args;
//	}
//
//	private void validateRunParam() {
//		if (isNoParamsPassed()) {
//			return;
//		} else if (isMoreThanOneParametersPassed()) {
//			throw new TooManyStartParamsPassedException();
//		}
//	}
//
//	private boolean isParamPrintHelp() {
//		return HELP_APP_PARAM_NAME.equals(args[0].trim());
//	}
//
//	private boolean isMoreThanOneParametersPassed() {
//		return args.length < 1;
//	}
//
//	private boolean isNoParamsPassed() {
//		return args.length == 0;
//	}
//
//	private void validateUserConfigFileParam() {
//		userConfigFile = extractUserConfigFileFromRunParam();
//
//		if (isUserConfigFilenameSuffixInvalid()) {
//			throw new InvalidConfigFilenameSuffix();
//		}
//
//		if (isUserConfigFileNotExists()) {
//			throw new UserConfigFileNotExists(userConfigFile.getFile()
//					.getName());
//		}
//	}
//
//	private FileSystemResource extractUserConfigFileFromRunParam() {
//		return new FileSystemResource(args[0]);
//	}
//
//	private void setUserConfigAsCurrentConfig() {
//		if (userConfigFile == null) {
//			currentConfigFileResource = extractUserConfigFileFromRunParam();
//
//		} else {
//			currentConfigFileResource = userConfigFile;
//		}
//	}
//
//	private void setDefaultConfigAsCurrentConfig() {
//		currentConfigFileResource = new ClassPathResource(
//				DEFAULT_PROPERTIES_FILENAME);
//	}
//
//	private boolean isUserConfigFilenameSuffixInvalid() {
//		return !userConfigFile.getFile().getName()
//				.endsWith(CONFIG_FILE_CORRECT_SUFFIX);
//	}
//
//	private boolean isUserProvideConfigFilename() {
//		return isRunParamsPresent();
//	}
//	
//	private boolean isUserConfigFileNotExists() {
//		return currentConfigFileResource.exists();
//	}
}
