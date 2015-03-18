package org.farynaa.servermanager.business.console.command.strategy;

/**
 * @author devil
 *
 */
public class QuitConsoleCommandStrategy extends AbstractConsoleCommandStrategy {

	@Override
	public void process(String[] params) {
		printQuitMessage();
	}
	
	private void printQuitMessage() {
		System.out.println("Bye!");
	}

	@Override
	public boolean isKeepConsoleRunning() {
		return false;
	}
}
