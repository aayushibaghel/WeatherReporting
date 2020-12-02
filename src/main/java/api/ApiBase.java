package api;

import io.restassured.RestAssured;

public class ApiBase 
{
	
	public void setup() 
	{
		RestAssured.baseURI = "http://api.openweathermap.org";
		RestAssured.basePath = "/data/2.5";
	}
	
	
	
}
