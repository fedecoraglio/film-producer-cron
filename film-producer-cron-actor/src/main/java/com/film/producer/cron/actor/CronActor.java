package com.film.producer.cron.actor;

import com.film.producer.cron.actor.config.ActorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CronActor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronActor.class);

    public static void main(String args[]) {
        LOGGER.info("Starting cron actor application");
        final ApplicationContext context = new AnnotationConfigApplicationContext(ActorConfig.class);
    }
}
