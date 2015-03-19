package org.farynaa.servermanager.business.console.command;

import java.util.HashMap;
import java.util.Map;

import org.farynaa.servermanager.business.console.command.strategy.AddServerConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.ConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.CountServersConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.DeleteServerConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.EditServerConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.HelpConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.ListServersConsoleCommandStrategy;
import org.farynaa.servermanager.business.console.command.strategy.QuitConsoleCommandStrategy;

/**
 * Factory for generating processing strategies for console commands.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class ConsoleCommandStrategyFactory {

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

	private ConsoleCommandStrategyFactory() {
		// do nothing
	}
}
