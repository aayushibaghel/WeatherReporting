package test;

import static configuration.Config.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.WeatherDataApi;
import ui.NdtvWeather;
import utility.BaseUtil;


public class ComparatorTest {
	
	NdtvWeather uiTest = new NdtvWeather();
	WeatherDataApi apiTest = new WeatherDataApi();
	
	@Test
	public void compareTemperatureInFahrenheit() {
		
		intializePropFile();
		float allowedDifference = Float.parseFloat(readProperty("allowedTemperatureDifference"));
		
		float ndtvTemp = BaseUtil.fahrenheitToKelvin(uiTest.getTemperatureInFahrenheit());
		float apiTemp = apiTest.getTemperatureInKelvin();
		System.out.println(ndtvTemp + "---" + apiTemp);
		Assert.assertEquals(Math.abs(ndtvTemp - apiTemp), allowedDifference);
	   
	}

}
