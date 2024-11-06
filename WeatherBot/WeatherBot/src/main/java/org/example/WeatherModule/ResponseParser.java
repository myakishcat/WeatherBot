/**
 * @file ResponseParser.java
 * @brief Класс для парсинга данных ответа от API погоды.
 *
 * Этот класс содержит метод для преобразования строки JSON, полученной в ответе от API погоды,
 * в объект типа `WeatherData` с использованием библиотеки Gson.
 */
package org.example.WeatherModule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @class ResponseParser
 * @brief Класс для парсинга JSON-ответов от API погоды.
 *
 * Этот класс предоставляет метод для преобразования строки JSON в объект `WeatherData` с помощью библиотеки Gson.
 */
public class ResponseParser {

    /**
     * @brief Парсит строку JSON в объект WeatherData.
     *
     * Этот метод принимает строку в формате JSON, представляющую ответ от API погоды, и преобразует её
     * в объект типа `WeatherData` с помощью библиотеки Gson.
     *
     * @param jsonResponse Строка JSON, полученная от сервера в ответ на запрос о погоде.
     * @return Объект `WeatherData`, содержащий данные о погоде, полученные из JSON.
     */
    public static WeatherData parseWeatherData(String jsonResponse) {
        Gson gson = new GsonBuilder().create();  ///< Создаем объект Gson для парсинга
        return gson.fromJson(jsonResponse, WeatherData.class);  ///< Преобразуем JSON в объект WeatherData
    }
}
