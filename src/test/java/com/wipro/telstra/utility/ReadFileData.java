package com.wipro.telstra.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFileData {

	Properties prop = new Properties();
	InputStream input = null;
	
	public String testUrl;
	public ReadFileData() {
		try {
			String currnetlocation = System.getProperty("user.dir");
			input = new FileInputStream(currnetlocation + "/src/test/resources/application.properties");

			prop.load(input);
			
			testUrl = prop.getProperty("testUrl");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return testUrl;

	}

}