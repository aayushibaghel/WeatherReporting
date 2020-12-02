package test;


import static configuration.Config.intializePropFile;
import static configuration.Config.readProperty;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.WeatherDataApi;
import ui.NdtvWeather;
import utility.BaseUtil;



public class WeatherDataApiTest
{
	NdtvWeather homePage = new NdtvWeather();
	WeatherDataApi apiTest = new WeatherDataApi();
	
	@Test
	public void validateTemperature() 
	{
		WeatherDataApi api = new WeatherDataApi();
		float temp = api.getTemperatureInKelvin();
		Assert.assertNotNull(temp);
		
	}
	
	@Test
	public void compareTemperatureInFahrenheit() {
		
		intializePropFile();
		float allowedDifference = Float.parseFloat(readProperty("allowedTemperatureDifference"));
		
		float ndtvTemp = BaseUtil.fahrenheitToKelvin(homePage.getTemperatureInFahrenheit());
		float apiTemp = apiTest.getTemperatureInKelvin();
		System.out.println(ndtvTemp + "---" + apiTemp);
		Assert.assertEquals(Math.abs(ndtvTemp - apiTemp), allowedDifference);
	   
	}
}
