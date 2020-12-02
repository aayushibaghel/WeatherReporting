package ui;

import java.util.ArrayList;
import java.util.List;
import static configuration.Config.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import constants.Constants;
import ui.UIBase;
import utility.BaseUtil;

public class NdtvWeather extends UIBase {
	UIBase base = new UIBase();
	ArrayList<String> weather = new ArrayList<String>();
	
	By extraLink = By.xpath("//a[@id='h_sub_menu']");
	By weatherLink = By.xpath("//a[text()='WEATHER']");
	By noThanksLink = By.xpath("//a[@class='notnow']");
	By pinYourCityTxtBox = By.id("searchBox");
	By selectCityChkBox = By.xpath("//div[@id='messages']//input");
	By cityText = By.xpath("//div[@class='temperatureContainer']/following-sibling::div");
	By cityTempInCelsius = By.xpath(findTemperatureUsingCityText()[0]);
	By cityTempInFarhn = By.xpath(findTemperatureUsingCityText()[1]);
	By weatherDetails = By.xpath(
			"//div[@class='leaflet-popup-content-wrapper']//div[@class='leaflet-popup-content']//span[@class='heading']/b");
	By weatherDetailsCloseBtn = By.xpath("//a[@class='leaflet-popup-close-button']");
	
	public void loadWeatherPage() {

		try {
			intializePropFile();
			handlePopUp();
			driver.findElement(extraLink).click();
			driver.findElement(weatherLink).click();
			Thread.sleep(5000);
			Assert.assertEquals(driver.getTitle(), readProperty("WeatherPageTitle"),
					"Weather Page Loaded Successfully");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void handlePopUp() {
		driver.findElement(noThanksLink).click();
	}

	public void validateCityOnMapWithTemp() {
		boolean isCityOnMap = false;

		driver.findElement(pinYourCityTxtBox).sendKeys(readProperty("City"));
		driver.findElement(selectCityChkBox).click();

		List<WebElement> cityList = driver.findElements(cityText);

		for (WebElement ele : cityList) {
			if (ele.getText().equals(readProperty("City"))) {
				isCityOnMap = true;
				break;
			}
		}
		if (isCityOnMap) {
			WebElement tempInCelcius = driver.findElement(cityTempInCelsius);
			WebElement tempInFarhn = driver.findElement(cityTempInFarhn);

			Assert.assertEquals(tempInCelcius.getText().contains(readProperty("TempInCelcius")), true);

			Assert.assertEquals(tempInFarhn.getText().contains(readProperty("TempInFarhn")), true);
		} else {
			System.out.println("City not present on Map");
		}
	}

	private String[] findTemperatureUsingCityText() {
		intializePropFile();
		String[] temp = new String[2];
		temp[0] = "//div[@id='map_canvas']//div[@class='leaflet-marker-icon my-div-icon leaflet-zoom-animated leaflet-interactive']//div[@title='"
				+ readProperty("City") + "']//span[@class='tempRedText']";
		temp[1] = "//div[@id='map_canvas']//div[@class='leaflet-marker-icon my-div-icon leaflet-zoom-animated leaflet-interactive']//div[@title='"
				+ readProperty("City") + "']//span[@class='tempWhiteText']";
		return temp;
	}

	public ArrayList<String> getWeatherDetailsForCity() {
		

		driver.findElement(cityTempInCelsius).click();

		List<WebElement> weatherDetailsList = driver.findElements(weatherDetails);

		for (WebElement weatherDetail : weatherDetailsList) {
			weather.add(weatherDetail.getText());
		}

		Assert.assertEquals(weather.size() != 0, true);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(weatherDetailsCloseBtn));

		driver.findElement(weatherDetailsCloseBtn).click();
		return weather;
	}

	public float getTemperatureInFahrenheit() {
		Float temperature = null;
		try {
			temperature = BaseUtil.getTemperatureFromUIWeatherDetails(getWeatherDetailsForCity(), Constants.uiTempFahrenheit);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temperature;
	}

	public float getTemperatureInDegrees() {
		Float temperature = null;
		try {
			temperature = BaseUtil.getTemperatureFromUIWeatherDetails(getWeatherDetailsForCity(), Constants.uiTempDegrees);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temperature;
	}

}
