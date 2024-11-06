/**
 * @file Main.java
 * @brief Главный класс для запуска Telegram бота.
 *
 * Этот класс отвечает за инициализацию и регистрацию бота в Telegram API.
 * Он запускает процесс работы с Telegram API, создавая экземпляр бота и регистрируя его с использованием `TelegramBotsApi`.
 */
package org.example;

import org.example.Bot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @class Main
 * @brief Главный класс для запуска Telegram бота.
 *
 * Этот класс инициализирует и регистрирует Telegram бота, используя Telegram Bots API.
 */
public class Main {

    /**
     * @brief Точка входа в программу для запуска Telegram бота.
     *
     * Метод `main` является точкой входа для запуска программы. Он инициализирует API ботов Telegram,
     * создаёт экземпляр бота и регистрирует его для получения обновлений.
     *
     * @param args Аргументы командной строки (не используются в данном классе).
     * @throws TelegramApiException Если возникла ошибка при регистрации бота в Telegram API.
     */
    public static void main(String[] args) throws TelegramApiException {
        // Инициализация API для работы с ботами
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        // Регистрация бота
        botsApi.registerBot(new Bot());
    }
}
