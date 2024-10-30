package org.example.WeatherModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ResponseParser {
    public static WeatherData parseWeatherData(String jsonResponse) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonResponse, WeatherData.class);
    }
}
