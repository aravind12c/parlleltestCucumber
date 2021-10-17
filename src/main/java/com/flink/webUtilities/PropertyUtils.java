package com.flink.webUtilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	/**
	 * Property Utils is used to get the value based on Key Parameter from Config
	 * Properties file.
	 */

	public static String configProperty(String key) {
		FileReader reader;
		Properties property = new Properties();
		try {
			reader = new FileReader("src/main/resources/WebConfig/config.properties");
			property.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return property.getProperty(key);
	}

}
