package net.joywise.bigdata.zid_rpc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class RpcServiceConfig {
	private Properties prop;
	private String filePath;
	private static Logger logger = Logger.getLogger(RpcServiceConfig.class);

	public RpcServiceConfig(String filePath) {
		prop = new Properties();
		this.filePath = filePath;
		reload();
	}

	public void reload() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
	        URL url = classLoader.getResource(filePath);
			prop.load(new FileInputStream(new File(url.getFile())));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	public void setProperty(String key, String value) {
		prop.setProperty(key, value);
	}
}
