package org.farynaa.servermanager;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.farynaa.servermanager.exception.ConfigFileNotExists;
import org.farynaa.servermanager.exception.InvalidConfigFilenameSuffix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class MainApp {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
	
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, IOException {
		
		boolean runInInteractiveMode = true;
		
		ClassPathResource defaultPropertiesResource = new ClassPathResource("default.properties");
		Properties defaultProperties = PropertiesLoaderUtils.loadProperties(defaultPropertiesResource);
		
		if (args.length == 1) {
			if ("help".equals(args[0])) {
				runInInteractiveMode = false;
				printHelp();
				
			} else {
				runInInteractiveMode = true;
				FileSystemResource fileSystemResource = new FileSystemResource(args[0]);
				
				if (!fileSystemResource.getFile().getName().endsWith(".properties")) {
					throw new InvalidConfigFilenameSuffix();
				}
				
				if (!fileSystemResource.exists()) {
					throw new ConfigFileNotExists(args[0]);
				}
				
				Properties userProperties = PropertiesLoaderUtils.loadProperties(fileSystemResource);
				defaultProperties.putAll(userProperties);
			}
		}
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation("application-context.xml");
		Map<String, Object> systemProperties = applicationContext.getEnvironment().getSystemProperties();
		for (String prop : defaultProperties.stringPropertyNames()) {
			systemProperties.put(prop, defaultProperties.getProperty(prop));
		}
		applicationContext.refresh();
		
//		proccessUserConfigurationFile();
		
		
		
//		boolean running = true;
//
//		showHelp();
//
		Console console = System.console();
		
		
		while (runInInteractiveMode) {
			String command = console.readLine("> ").trim();
			LOGGER.info(command);
			
//			DataInputStream in = new DataInputStream(System.in);
//			String option = in.readLine();

			if (command.startsWith("help")) {
				
			} else if (command.startsWith("quit")) {
				runInInteractiveMode = false;
				
			} else if (command.startsWith("addServer")) {
				addServerCommand();
				
			} else if (command.startsWith("deleteServer")) {
				deleteServerCommand();
				
			} else if (command.startsWith("editServer")) {
				editServerServerCommand();
				
			} else if (command.startsWith("listServers")) {
				listServersCommand();
			}
		}
	}

	private static void listServersCommand() {
		LOGGER.info("list severs");
	}

	private static void editServerServerCommand() {
		LOGGER.info("edit server");
	}

	private static void deleteServerCommand() {
		LOGGER.info("delete server");
	}

	private static void addServerCommand() {
		LOGGER.info("add server");
	}


	private static void printHelp() {
		// TODO Auto-generated method stub
		
	}



	private static boolean isUserPassRunParameters(String[] args) {
		return args.length == 0;
	}
	


	private static void showHelp() {
		System.out.println("help to display this message");
		System.out
				.println("countServers to display the current number of servers present");
		System.out
				.println("addServer to display the current number of servers present");
		System.out
				.println("editServer to change the name of a server identified by id (takes 2 additional args - the id and the new name)");
		System.out
				.println("deleteServer to delete a server (takes one more arg - the id of the server to delete)");
		System.out
				.println("listServers to display details of all servers servers");
	}
}
