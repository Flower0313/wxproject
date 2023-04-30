package com.holden.wxproject.controller;

import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.config.ExecutorConfig;
import com.holden.wxproject.mapper.BotMapper;
import com.holden.wxproject.mapper.IndexMapper;
import com.holden.wxproject.pojo.HoldenOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.holden.wxproject.config.BaseConstant.DELAY_SECONDS;

/**
 * @ClassName wxproject-WriteTest
 * @Athor Holden_-__--___Xiao
 * @Create 2023年1月30日23:09 - 周一
 * @Describe
 */
@RestController
public class MyBot extends TelegramLongPollingBot {

    //@Resource(name = "dataHandleThreadPool")
    //private final ScheduledExecutorService executor = ExecutorConfig.dataHandleThreadPool();

    @Autowired
    private BotMapper botMapper;

    public MyBot() {
        super(new HoldenOptions(), BaseConstant.TOKEN);
    }


    /**
     * @return bot_api创建者id
     */
    public int creatorId() {
        return 313;
    }


    /**
     * 处理方法
     *
     * @param update 用户消息
     */
    @Override
    @SourceChange(BaseConstant.NURSING)
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            //checkAuth(update.getMessage().getChat());
            try {
                sendTextRecall(update);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 简单发送给消息
     *
     * @param message 消息
     */
    private void sendText(SendMessage message) throws TelegramApiException {
        executeAsync(message);
    }

    /**
     * 带撤回的消息发送
     *
     * @param update 用户消息
     */
    private void sendTextRecall(Update update) throws TelegramApiException {
        Long chatId = update.getMessage().getChatId();
        Message execute = execute(SendMessage.builder()
                .chatId(chatId)
                .text(update.getMessage().getText())
                .build());
//        executor.schedule(() -> {
//            DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), execute.getMessageId());
//            try {
//                execute(deleteMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }, DELAY_SECONDS, TimeUnit.SECONDS);
//        executor.shutdown();
    }


    /**
     * @return 机器人用户名
     */
    @Override
    public String getBotUsername() {
        return BaseConstant.BOT_USERNAME;
    }

    /**
     * @return 机器人token
     */
    @Override
    public String getBotToken() {
        return BaseConstant.TOKEN;
    }


    /**
     * 只保存付款用户的信息，不存聊天用户信息
     *
     * @param chat 用户信息
     */
    private void checkAuth(Chat chat) {
        if (!BaseConstant.ZERO.equals(botMapper.ifExist(chat.getUserName()))) {
            botMapper.insert(chat);
        }
    }

    /**
     * 查询余额
     *
     * @param chat 用户信息
     * @return @Link 余额
     */
    private String checkBalance(Chat chat) {
        return String.valueOf(botMapper.balance(chat.getUserName()));
    }
}

