package org.farynaa.servermanager.business.console.command.strategy;

/**
 * @author devil
 *
 */
public class CountServersConsoleCommandStrategy extends AbstractConsoleCommandStrategy {

	@Override
	public void process(String[] params) {
		getServerService().countServers();
	}
}
