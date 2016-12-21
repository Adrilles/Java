package br.com.ticketballws.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	public static Properties getProp(InputStream inputStream) throws IOException {
		Properties props = new Properties();
		
		if (inputStream != null) {
			props.load(inputStream);
		} else {
			throw new FileNotFoundException("property file not found in the classpath");
		}

		return props;

	}
	
	public static Properties getProp(String config) throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(config);
		props.load(file);
		return props;

	}
	
	

}
