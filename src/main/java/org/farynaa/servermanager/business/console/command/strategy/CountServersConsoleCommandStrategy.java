package org.farynaa.servermanager.business.console.command.strategy;

/**
 * @author devil
 *
 */
public class CountServersConsoleCommandStrategy extends AbstractConsoleCommandStrategy {

	@Override
	public void process(String[] params) {
		int count = getServerService().countServers();
		printMessage(count);
	}

	private void printMessage(int count) {
		String message = String.format("There are '%d' servers saved in database.", count);
		System.out.println(message);
	}
}
