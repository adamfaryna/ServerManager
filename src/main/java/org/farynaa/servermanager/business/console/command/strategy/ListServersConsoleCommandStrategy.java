package org.farynaa.servermanager.business.console.command.strategy;

import java.util.List;

import org.farynaa.servermanager.business.model.entity.Server;

/**
 * @author devil
 *
 */
public class ListServersConsoleCommandStrategy extends AbstractConsoleCommandStrategy {
	
	@Override
	public void process(String[] params) {
		List<Server> servers = getServerService().listServers();
		printMessage(servers);
	}

	private void printMessage(List<Server> servers) {
		String message = new String("Persisted servers:\n");
		if (servers.size() == 0) {
			message += "empty";
			
		} else {
			for (Server server : servers) {
				message += server.toString() + "\n";
			}			
		}

		System.out.println(message);
	}
}
