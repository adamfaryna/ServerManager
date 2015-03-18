package org.farynaa.servermanager.business.console.command.strategy;

import java.util.HashMap;
import java.util.Map;

import org.farynaa.servermanager.console.ConsoleCommand;

/**
 * @author devil
 *
 */
public abstract class ConsoleCommandFactory {

	private static final Map<ConsoleCommand, ConsoleCommandStrategy> COMMAND_STRATEGY_MAPPING = new HashMap<ConsoleCommand, ConsoleCommandStrategy>(ConsoleCommand.values().length);
	
	static {
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.HELP, new HelpConsoleCommandStrategy());
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.QUIT, new QuitConsoleCommandStrategy());
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.ADD_SERVER, new AddServerConsoleCommandStrategy());
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.EDIT_SERVER, new EditServerConsoleCommandStrategy());
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.DELETE_SERVER, new DeleteServerConsoleCommandStrategy());
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.COUNT_SERVERS, new CountServersConsoleCommandStrategy());
		COMMAND_STRATEGY_MAPPING.put(ConsoleCommand.LIST_SERVERS, new ListServersConsoleCommandStrategy());
	}
	
	public static final ConsoleCommandStrategy getStrategyForCommandName(String commandName) {
		ConsoleCommand command = ConsoleCommand.getForString(commandName);
		return COMMAND_STRATEGY_MAPPING.get(command);
	}

	private ConsoleCommandFactory() {
		// do nothing
	}
}
