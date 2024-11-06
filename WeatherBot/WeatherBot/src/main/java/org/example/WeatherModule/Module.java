/**
 * @file Module.java
 * @brief Модуль для выполнения HTTP-запросов к API погоды.
 *
 * Класс предоставляет метод для выполнения запроса к внешнему API погоды, получения ответа
 * и парсинга полученных данных в объект модели WeatherData.
 */
package org.example.WeatherModule;

import java.net.MalformedURLException;

import static org.example.WeatherModule.ResponseParser.parseWeatherData;

/**
 * @class Module
 * @brief Модуль для запроса и обработки данных погоды.
 *
 * Этот класс отправляет HTTP-запрос к API погоды, получает ответ, а затем парсит данные
 * для дальнейшего использования.
 */
public class Module {

    /**
     * @brief Выполняет запрос к API погоды и возвращает объект WeatherData.
     *
     * Этот метод формирует запрос к внешнему API погоды, отправляет его, получает
     * ответ и парсит данные о погоде в объект WeatherData.
     *
     * @return Объект WeatherData, содержащий информацию о текущей погоде.
     * @throws RuntimeException Если произошла ошибка при выполнении запроса или обработке данных.
     */
    public WeatherData Request() {
        // Формирование URL для запроса
        String url = "https://api.openweathermap.org/data/2.5/weather?" +
                "lat=56.858&" +
                "lon=60.612&" +
                "exclude=minutely,hourly&" +
                "lang=ru&" +
                "appid=token&";
        try
        {
            // Создаем клиент для выполнения HTTP-запроса
            Client weatherClient = new Client(url);
            weatherClient.Connect(); // Устанавливаем соединение
            weatherClient.SetMethod("GET"); // Устанавливаем метод запроса

            try
            {
                // Получаем ответ от сервера
                Response testResponse = new Response(
                        weatherClient.GetResponseCode(),
                        weatherClient.GetInputStream()
                );

                // Получаем данные из ответа
                testResponse.getResponseData();

                // Парсим данные погоды в объект WeatherData
                WeatherData weatherResponse = parseWeatherData(testResponse.getResponseData());

                return weatherResponse;
            }
            catch (Exception e)
            {
                // Обрабатываем исключение, если произошла ошибка при обработке ответа
                throw new RuntimeException(e);
            }
        }
        catch (MalformedURLException e)
        {
            // Обрабатываем исключение, если произошла ошибка при формировании URL
            throw new RuntimeException(e);
        }
    }
}
