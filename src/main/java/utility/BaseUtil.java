package utility;

import java.util.ArrayList;

public class BaseUtil {

	public static float getTemperatureFromUIWeatherDetails(ArrayList<String> weather, String parameterType) {
		Float temperature = null;
		for (String value : weather) {
			if (value.contains(parameterType)) {
				System.out.println(weather.get(weather.indexOf(value)).split(":")[1].trim());
				temperature = Float.parseFloat(weather.get(weather.indexOf(value)).split(":")[1].trim());
			}
		}

		return temperature;

	}

	public static float fahrenheitToKelvin(float F) {
		return 273.5f + ((F - 32.0f) * (5.0f / 9.0f));
	}

	public static float celsiusToKelvin(float C) {
		return (float) (C + 273.15);
	}

}
