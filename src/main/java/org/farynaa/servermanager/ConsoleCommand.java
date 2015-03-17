package org.farynaa.servermanager;

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
}
