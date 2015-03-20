package org.farynaa.servermanager.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 * @author adamfaryna@gmail.com
 *
 */
public final class TestUtils {

	public static InputStream objectToInputStream(Object obj) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new ByteArrayInputStream(baos.toByteArray());
	}

	private TestUtils() {
		// do nothing
	}
}
