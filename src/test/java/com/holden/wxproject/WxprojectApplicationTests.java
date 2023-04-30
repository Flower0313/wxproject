package com.holden.wxproject;

import com.holden.wxproject.config.ExecutorConfig;
import com.holden.wxproject.controller.MyBot;
import com.holden.wxproject.pojo.resp.OneRowResp;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;


class WxprojectApplicationTests {
    public static final String TOKEN = "6048362883:AAHYWhv1ZEh2fL5f6LUN3FWLXdSQ5s6GdtU";

    public static void main(String[] args) throws TelegramApiException {
        /*// 设置日志级别为WARNING

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        DefaultBotOptions botOptions = new DefaultBotOptions();

        botOptions.setProxyHost("127.0.0.1");
        botOptions.setProxyPort(7890);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        //MyBot bot = new MyBot(TOKEN, botOptions);

        //botsApi.registerBot(bot);*/

        ExecutorService executorService = ExecutorConfig.pool;

        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            return "你好";
        });




    }
}
