package ru.dartanum.requestmanager.fw;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "ru.dartanum.requestmanager")
@ComponentScan(basePackages = "ru.dartanum.requestmanager")
@EntityScan(basePackages = "ru.dartanum.requestmanager.domain")
@EnableMongoRepositories(basePackages = "ru.dartanum.requestmanager.adapter.persistence")
public class ApplicationConfiguration {
}
