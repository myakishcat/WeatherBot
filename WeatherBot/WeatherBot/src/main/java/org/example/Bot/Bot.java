/**
 * @file Bot.java
 * @brief Класс для реализации Telegram бота для получения данных о погоде.
 *
 * Этот класс реализует бота для Telegram, который использует модуль погоды для получения данных
 * о текущей погоде и отправки этих данных пользователю через Telegram.
 */
package org.example.Bot;

import org.example.WeatherModule.Module;
import org.example.WeatherModule.WeatherData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @class Bot
 * @brief Класс для работы с Telegram ботом, предоставляющим информацию о погоде.
 *
 * Этот класс расширяет `TelegramLongPollingBot` и реализует методы для обработки обновлений от пользователей,
 * а также отправки сообщений с информацией о текущей погоде.
 */
public class Bot extends TelegramLongPollingBot {
    /** Имя пользователя бота в Telegram. */
    private final String BotUsername = "ScrumWeatherBot";

    /** Токен бота для доступа к API Telegram. */
    private final String BotToken = "token";

    /**
     * @brief Получение имени пользователя бота.
     *
     * Этот метод возвращает имя пользователя бота, которое будет использоваться при взаимодействии с Telegram API.
     *
     * @return Имя пользователя бота в Telegram.
     */
    @Override
    public String getBotUsername() {
        return BotUsername;
    }

    /**
     * @brief Получение токена бота.
     *
     * Этот метод возвращает токен, который используется для аутентификации бота в Telegram API.
     *
     * @return Токен бота для доступа к API Telegram.
     */
    @Override
    public String getBotToken() {
        return BotToken;
    }

    /**
     * @brief Обработка обновлений от пользователей.
     *
     * Этот метод вызывается при получении обновления (например, нового сообщения) от пользователя. Он извлекает
     * текст сообщения и информацию о пользователе, а затем получает данные о погоде с помощью модуля погоды.
     *
     * @param update Объект, содержащий информацию о сообщении и пользователе.
     */
    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());  ///< Логируем имя пользователя и текст сообщения.

        // Получаем данные о погоде
        Module weatherModule = new Module();
        WeatherData data = weatherModule.Request();

        // Получаем и выводим данные о температуре, давлении и влажности
        data.getHumidity();
        data.getPressure();
        data.getTemp();
    }

    /**
     * @brief Отправка текстового сообщения пользователю.
     *
     * Этот метод отправляет текстовое сообщение пользователю по его ID в Telegram.
     *
     * @param receiver Идентификатор чата, которому будет отправлено сообщение.
     * @param text Текст сообщения, которое будет отправлено пользователю.
     * @throws RuntimeException Если произошла ошибка при отправке сообщения.
     */
    public void sendText(Long receiver, String text){
        SendMessage message = SendMessage.builder()
                .chatId(receiver.toString())
                .text(text)
                .build();

        try {
            execute(message);  // Отправляем сообщение через API Telegram
        }
        catch(TelegramApiException APIexception) {
            // Обработка исключений при отправке сообщения
            throw new RuntimeException("Failed to send message", APIexception);
        }
    }
}
