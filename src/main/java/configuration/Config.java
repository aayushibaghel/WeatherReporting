package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	static File file;
	static Properties prop;
	static FileInputStream fis;

	public static void intializePropFile() {
		String configFilePath = System.getProperty("user.dir") + "//src//main//resources//config.properties";

		file = new File(configFilePath);
		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException found in intializePropFile: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException found in intializePropFile: " + e.getMessage());
		}

	}

	public static String readProperty(String property) {
		return prop.getProperty(property);
	}

}
