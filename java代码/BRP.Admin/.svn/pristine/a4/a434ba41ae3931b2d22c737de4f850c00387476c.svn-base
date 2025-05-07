package strosoft.app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertyHelper {
	public static String getProperty(String filePath, String propertyName) throws IOException, FileNotFoundException {

		Properties theProperties = new Properties();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(
				filePath), "utf-8");
		theProperties.load(isr);
		String propertyValue = theProperties.getProperty(propertyName);
		return propertyValue;
	}
}
