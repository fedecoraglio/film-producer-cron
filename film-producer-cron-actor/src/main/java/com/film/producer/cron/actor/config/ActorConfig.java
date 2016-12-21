package com.film.producer.cron.actor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("com.film.producer.cron.actor")
@PropertySource({"classpath:film-producer-cron-actor.properties"})
public class ActorConfig {

}
