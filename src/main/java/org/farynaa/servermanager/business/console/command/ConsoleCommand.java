package org.farynaa.servermanager.business.console.command;

import org.farynaa.servermanager.business.exception.validation.console.InvalidCommandException;

/**
 * @author devil
 *
 */
public enum ConsoleCommand {
	HELP("help"),
	QUIT("quit"),
	LIST_SERVERS("listServers"),
	COUNT_SERVERS("countServers"),
	ADD_SERVER("addServer"),
	DELETE_SERVER("deleteServer"),
	EDIT_SERVER("editServer");
	
	private String command;
	
	private ConsoleCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public static ConsoleCommand getForString(String commandString) {
		String commandStringSeparated = commandString.split(" ")[0];
		for (ConsoleCommand command : values()) {
			if (commandStringSeparated.equals(command.getCommand())) {
				return command;
			}
		}
		throw new InvalidCommandException(commandStringSeparated);
	}
}
