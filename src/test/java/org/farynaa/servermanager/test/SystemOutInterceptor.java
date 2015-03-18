package org.farynaa.servermanager.test;

import java.io.OutputStream;
import java.io.PrintStream;

public class SystemOutInterceptor extends PrintStream {

	private String interceptedString;
	
	public SystemOutInterceptor(OutputStream out) {
		super(out, true);
	}

	@Override
	public void println(String string) {
		interceptedString = string;
		super.println(string);
	}

	public String getInterceptedString() {
		return interceptedString;
	}
}
