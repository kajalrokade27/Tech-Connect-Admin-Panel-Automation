package com.TechConnect.FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetPropertyData 
{
	public static String propData(String key) throws IOException{
		  Properties prop = new Properties();
		  FileInputStream fis = new FileInputStream("src/test/resources/Config/LoginData.Properties");
		  prop.load(fis);
		 String data= prop.getProperty(key);
		 return data;
	}
}
