package com.holden.wxproject;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @ClassName wxproject-WriteTest
 * @Athor Holden_-__--___Xiao
 * @Create 2023年1月30日23:09 - 周一
 * @Describe
 */

public class MyBot extends TelegramLongPollingBot {
    protected MyBot(String botToken, DefaultBotOptions botOptions) {
        super(botOptions,botToken);
    }

    public int creatorId() {
        return 0;
    }


    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText() + ">>>");
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "yellowdudebot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "6048362883:AAHYWhv1ZEh2fL5f6LUN3FWLXdSQ5s6GdtU";
    }
}

