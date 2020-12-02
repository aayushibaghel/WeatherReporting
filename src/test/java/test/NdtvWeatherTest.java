package test;

import org.testng.annotations.Test;
import ui.NdtvWeather;
import ui.UIBase;


import static configuration.Config.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class NdtvWeatherTest extends NdtvWeather
{
	NdtvWeather homePage = new NdtvWeather();

	UIBase base = new UIBase();
	
	@BeforeSuite(alwaysRun=true)
	public void intializeTest()
	{
		intializePropFile();
		
	}
	
	@Test
	public void weatherPageTest()
	{
		base.launchBrowser();
		homePage.loadWeatherPage();
		homePage.validateCityOnMapWithTemp();
		homePage.getWeatherDetailsForCity();
	}

	@AfterSuite
	public void closeBrowser()
	{
		driver.close();
	}
	

}
