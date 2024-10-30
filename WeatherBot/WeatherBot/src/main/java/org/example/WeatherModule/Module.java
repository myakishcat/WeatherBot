package org.example.WeatherModule;
import java.net.MalformedURLException;

import static org.example.WeatherModule.ResponseParser.parseWeatherData;

public class Module {
    public WeatherData Request() {
        String url = "https://api.openweathermap.org/data/2.5/weather?" +
                "lat=56.858&" +
                "lon=60.612&" +
                "exclude=minutely,hourly&" +
                "lang=ru&" +
                "appid=token&";
        try
        {
            Client weatherClient = new Client(url);
            weatherClient.Connect();
            weatherClient.SetMethod("GET");

            try
            {
                Response testResponse = new Response(
                        weatherClient.GetResponseCode(),
                        weatherClient.GetInputStream()
                        );

                testResponse.getResponseData();

                WeatherData weatherResponse = parseWeatherData(testResponse.getResponseData());

                return weatherResponse;
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }

    }
}