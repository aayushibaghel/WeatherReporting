package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import constants.Constants;
import static configuration.Config.*;
public class UIBase 
{
	public static WebDriver driver;
	
	public  void launchBrowser()
	{
		
		if(readProperty(Constants.BROWSERNAME).equals("Chrome"))
			
		{
			System.setProperty("webdriver.chrome.driver", readProperty("chromeDriverPath"));
			
			Map<String, Object> prefs = new HashMap<String, Object>();
	        prefs.put("profile.default_content_setting_values.notifications", 2);
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			driver.get(readProperty(Constants.APPLICATION));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		}
	}
	
	
	
	

}
