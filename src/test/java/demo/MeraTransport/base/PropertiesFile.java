package demo.MeraTransport.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	private Properties prop = new Properties();
	static String PROPERTY_FILENAME = System.getProperty("user.dir")+"/Configuration/Config.properties";

	/**
	 * returns the value of the property denoted by key
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(final String key) {
		try {
			prop.load(new FileInputStream(PROPERTY_FILENAME));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		assert !prop.isEmpty();
		String property = prop.getProperty(key);
		return property != null ? property.trim() : property;
	}
}