package ru.dartanum.requestmanager.adapter.telegram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "telegram.bot")
public class BotProperties {
    private String token;
    private String username;
    private String chatId;
}
