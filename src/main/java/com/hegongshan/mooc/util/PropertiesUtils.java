package com.hegongshan.mooc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	private static Properties prop;
	
	static { 
		prop = new Properties();
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("qiniu.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			logger.error("导入qiniu.properties失败",e);
		}
	}
	public static String getProperties(String key) {
		return prop.getProperty(key);
	}
}
