package br.com.ticketballws.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static Properties getProp(String config) throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(config);
		props.load(file);
		return props;

	}

}
