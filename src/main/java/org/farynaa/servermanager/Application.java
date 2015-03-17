package org.farynaa.servermanager;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.farynaa.servermanager.exception.ConfigFileNotExistsException;
import org.farynaa.servermanager.exception.InvalidConfigFilenameSuffixException;
import org.farynaa.servermanager.exception.console.AdditionalParametersRequired;
import org.farynaa.servermanager.exception.console.InvalidParameterException;
import org.farynaa.servermanager.service.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Application {

	private static final String CONSOLE_PROMPT = "> ";
	private static final String SPRING_APPLICATION_CONTEXT_XML_FILENAME = "application-context.xml";
	private static final String DEFAULT_PROPERTIES = "default.properties";

	private Properties defaultProperties;
	private ClassPathXmlApplicationContext applicationContext;
	private ServerService serverService;

	private String[] args;

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		new Application(args).start();
	}

	public Application(String[] args) {
		this.args = args;
	}

	private void start() {
		if (isOnlyPrintHelp()) {
			showAppHelp();
			return;
		}

		prepareAndInitSpringContext();
		
		try {
			startConsoleProcessing();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void startConsoleProcessing() {
		Console console = System.console();
		boolean keepRunning = true;

		while (keepRunning) {
			String command = console.readLine(CONSOLE_PROMPT).trim();

			if (isHelpCommand(command)) {
				showConsoleHelp();

			} else if (isQuitCommand(command)) {
				keepRunning = false;

			} else  if (isListServersCommand(command)) {
					listServersCommand();
					
			} else {
				List<String> commandParameters = extractCommandParameters(command);
				
				if (isAddServerCommand(command)) {
					addServerCommand(commandParameters);

				} else if (isDeleteServerCommand(command)) {
					deleteServerCommand(commandParameters);

				} else if (isEditServerCommand(command)) {
					editServerCommand(commandParameters);

				} else {
					String message = String
							.format("Invalid command '%s'. Type 'help' to get list of possible commands.", command);
					System.out.println(message);
				}
			}
		}
	}

	private boolean isEditServerCommand(String command) {
		return command.startsWith(ConsoleCommand.EDIT_SERVER.getCommand());
	}

	private boolean isDeleteServerCommand(String command) {
		return command.startsWith(ConsoleCommand.DELETE_SERVER.getCommand());
	}

	private boolean isAddServerCommand(String command) {
		return command.startsWith(ConsoleCommand.ADD_SERVER.getCommand());
	}

	private boolean isListServersCommand(String command) {
		return command.startsWith(ConsoleCommand.LIST_SERVERS.getCommand());
	}

	private boolean isQuitCommand(String command) {
		return command.startsWith(ConsoleCommand.QUIT.getCommand());
	}

	private boolean isHelpCommand(String command) {
		return command.startsWith(ConsoleCommand.HELP.getCommand());
	}

	private List<String> extractCommandParameters(String command) {
		String[] splitedCommandString = command.split(" ");
		List<String> commandParameters = Arrays.asList(splitedCommandString).subList(0, splitedCommandString.length);
		return commandParameters;
	}
	

	private boolean isOnlyPrintHelp() {
		return isExtraParamPassed() && "help".equals(getExtraStartupParam());
	}

	private boolean isExtraParamPassed() {
		return args.length == 1;
	}

	private void validate(FileSystemResource fileSystemResource) {
		if (!fileSystemResource.getFilename().endsWith(".properties")) {
			throw new InvalidConfigFilenameSuffixException();
		}

		if (!fileSystemResource.exists()) {
			throw new ConfigFileNotExistsException(fileSystemResource.getFilename());
		}
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
		FileSystemResource fileSystemResource = new FileSystemResource(
				getExtraStartupParam());
		validate(fileSystemResource);
		Properties userProperties = extractPropertiesFromResource(fileSystemResource);
		return userProperties;
	}

	private boolean isExternalConfigFilenamePassed() {
		return isExtraParamPassed() && !"help".equals(getExtraStartupParam());
	}

	private String getExtraStartupParam() {
		return args[0];
	}

	private void initSpringContext() {
		applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation(SPRING_APPLICATION_CONTEXT_XML_FILENAME);

		updateSpringContextProperties();
		applicationContext.refresh();
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

	private void listServersCommand() {
		getServerService().listServers();
	}

	private void editServerCommand(List<String> commandParameters) {
		validateEditServerCommandParams(commandParameters);
		
		String serverIdString = commandParameters.get(0);
		Long serverId = Long.valueOf(serverIdString);
		String newServerName = commandParameters.get(1);
		
		getServerService().editServer(serverId, newServerName);
		printEditServerCommandSuccessMessage(serverId, newServerName);
	}

	private void printEditServerCommandSuccessMessage(Long serverId, String newServerName) {
		String message = String.format("Name of the server with id '%d' has been changed to '%s'.", serverId, newServerName);
		System.out.println(message);	
	}

	private void validateEditServerCommandParams(List<String> commandParameters) {
		if (commandParameters.size() != 2) {
			throw new AdditionalParametersRequired();
		}
		
		String serverIdCandidateString = commandParameters.get(0);
		validateCorrectServerId(serverIdCandidateString);
	}
	
	private void validateCorrectServerId(String serverIdCandidateString) {
		try {
			Long.valueOf(serverIdCandidateString);
			
		} catch (NumberFormatException e) {
			throw new InvalidParameterException();
		}
	}

	private void deleteServerCommand(List<String> commandParameters) {
		validateDeleteServerCommandParams(commandParameters);
		
		String serverIdString = commandParameters.get(0);
		Long serverId = Long.valueOf(serverIdString);
		
		getServerService().deleteServer(serverId);
		printDeleteServerCommandSuccessMessage(serverId);
	}

	private void printDeleteServerCommandSuccessMessage(Long serverId) {
		String message = String.format("Server with id '%s' has been deleted.", serverId);
		System.out.println(message);
	}

	private void validateDeleteServerCommandParams(List<String> commandParameters) {
		if (commandParameters.size() == 0) {
			throw new AdditionalParametersRequired();
		}
		
		String serverIdCandidateString = commandParameters.get(0);
		validateCorrectServerId(serverIdCandidateString);
	}

	private void addServerCommand(List<String> commandParameters) {
		LOGGER.info("add server");
		// TODO add
	}

	private void showAppHelp() {
		String helpContent = "This application works in interactive mode. You can pass your config parameters in format of java properties file as shown below. "
				+ "If you do not provide your own properites file default one will be used.\n\n"
				+ "Possible start options:\n"
				+ "server-manager help - prints this help\n"
				+ "server-manager properties-filename\n\n"
				+ "example of properties file:"
				+ "database.filename: dbhome\n"
				+ "database.name: database"
				+ "database.user: dbuser"
				+ "database.password: dbpass";
		System.out.println(helpContent);
	}

	private static void showConsoleHelp() {
		String message = "Possible commands:\n\n"
				+ "help - display this message\n"
				+ "countServers - display the current number of persisted servers\n"
				+ "addServer - persist server from spec file to database, takes additional arg - spec file path and name\n"
				+ "editServer - change the name of a server identified by id (takes 2 additional args - the id and the new name)\n"
				+ "deleteServer - delete a server (takes one more arg - the id of the server to delete)\n"
				+ "listServers - display details of all servers servers";
		System.out.println(message);
	}

	private ServerService getServerService() {
		if (serverService == null) {
			serverService = applicationContext.getBean(ServerService.class);
		}
		return serverService;
	}
}
