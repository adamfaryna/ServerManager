package org.farynaa.servermanager.business.console.command.strategy;

/**
 * Interface for console command.
 * 
 * @author adamfaryna@gmail.com
 */
public interface ConsoleCommandStrategy {

	/**
	 * Process this strategy for passed params.
	 * @param params
	 */
	void process(String[] params);
	
	/**
	 * @return <code>true</code> if we want to keep application console running after processing, otherwise <code>false</code>.
	 */
	boolean isKeepConsoleRunning();
	
}
