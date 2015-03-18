package org.farynaa.servermanager.business.console.command.strategy;

/**
 * @author devil
 *
 */
public interface ConsoleCommandStrategy {

	void process(String[] params);
	
	boolean isKeepConsoleRunning();
	
}
