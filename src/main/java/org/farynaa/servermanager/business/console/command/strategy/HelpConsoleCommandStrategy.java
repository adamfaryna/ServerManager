package org.farynaa.servermanager.business.console.command.strategy;


/**
 * Implementation of {@link ConsoleCommandStrategy} for 'help' console command.
 * 
 * @author adamfaryna@gmail.com
 */
public class HelpConsoleCommandStrategy extends AbstractConsoleCommandStrategy {
	
	private static final String CONSOLE_HELP_CONTENT = "Possible commands:\n"
			+ "help - display this message\n"
			+ "countServers - display the current number of persisted servers\n"
			+ "addServer - persist server from spec file to database, takes additional arg - spec file path and name\n"
			+ "editServer - change the name of a server identified by id (takes 2 additional args - the id and the new name)\n"
			+ "deleteServer - delete a server (takes one more arg - the id of the server to delete)\n"
			+ "listServers - display details of all servers servers";
	
	@Override
	public void process(String[] params) {
		System.out.println(CONSOLE_HELP_CONTENT);
	}
}
