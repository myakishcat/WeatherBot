package org.example.Bot;
import org.example.WeatherModule.Module;
import org.example.WeatherModule.WeatherData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.management.modelmbean.ModelMBean;

public class Bot extends TelegramLongPollingBot {
    private final String BotUsername = "ScrumWeatherBot";
    private final String BotToken = "token";

    @Override
    public String getBotUsername() {
        return BotUsername;
    }

    @Override
    public String getBotToken() {
        return BotToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());
        Module weatherModule = new Module();
        WeatherData data = weatherModule.Request();
        data.getHumidity();
        data.getPressure();
        data.getTemp();
    }

    public void sendText(Long receiver, String text){
        SendMessage message = SendMessage.builder().
                chatId(receiver.toString()).
                text(text).
                build();
        try
        {
            execute(message);
        }
        catch(TelegramApiException APIexception)
        {
            throw new RuntimeException();
        }
    }

}