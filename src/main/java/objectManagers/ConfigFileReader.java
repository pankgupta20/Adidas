package objectManagers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;
import utils.Constants;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = Constants.rootPath + "/src/main/resources/Configs/config.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public String getDriverPath() {
		String driverPath = Constants.rootPath + properties.getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException(
					"Driver Path not specified in the Configuration.properties file for the Key:driverPath");
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null) {
			try {
				return Long.parseLong(implicitlyWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;
	}

	public long getPageLoadWait() {
		String pageLoadTimeout = properties.getProperty("pageLoadTimeout");
		if (pageLoadTimeout != null) {
			try {
				return Long.parseLong(pageLoadTimeout);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + pageLoadTimeout + " in to Long");
			}
		}
		return 30;
	}

	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException(
					"Application Url not specified in the Configuration.properties file for the Key:url");
	}

	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if (browserName == null || browserName.equals("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equals("iexplorer"))
			return DriverType.INTERNETEXPLORER;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if (windowSize != null)
			return Boolean.valueOf(windowSize);
		return true;
	}

	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
}