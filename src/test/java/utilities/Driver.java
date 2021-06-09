package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;


public class Driver {
	
	/* Driver class is reusable class for webDriver and it checks the webDriver on the system.
	 * If there isn't any driver on the system, it downloads the driver and sets up the path and environment  
	 * For this purpose, I've used WebDriver manager
	 * And if I want to run my script on different browser, 
	 * all I have to do is change the browser name in the properties file.
	 */
	
private static WebDriver driver;
	
	public static WebDriver getDriver() {
		
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = PropertiesReader.getProperty("browser");
		}
		
		if(driver == null || ((RemoteWebDriver)driver).getSessionId() == null) {
			switch (browser) {
			case "firefox":
				//System.setProperty("webdriver.gecko.driver", PropertiesReader.getProperty("firefoxpath"));
				FirefoxDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "ie":
				//System.setProperty("webdriver.ie.driver", PropertiesReader.getProperty("ie"));
				InternetExplorerDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "safari":
				//System.setProperty("webdriver.ie.driver", PropertiesReader.getProperty("chromepath"));
				driver = new SafariDriver();
				break;
			default:
				//System.setProperty("webdriver.chrome.driver", PropertiesReader.getProperty("chromepath"));
				ChromeDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		}
		
		return driver;
	}
	
	public static void quitDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

	

}
