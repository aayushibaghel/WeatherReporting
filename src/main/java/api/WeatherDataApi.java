package api;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import static configuration.Config.*;

public class WeatherDataApi extends ApiBase
{

	public WeatherDataApi() 
	{
		super.setup();
	}
	

	public Response getWeatherDataByCity(String city) 
	{

		intializePropFile();
		
		Response response = given()
				.param("q", city)
				.param("appid", readProperty("ApiKey"))
				 .when()
				 .get("/weather");
		
		return response;
	}
	
	public float getTemperatureInKelvin() 
	{
		intializePropFile();
		
		Response response = getWeatherDataByCity(readProperty("City"));
		Map<String, Float> tempDetails = response.jsonPath().getMap("main");
	
		return tempDetails.get("temp");
	} 
	
	
}
