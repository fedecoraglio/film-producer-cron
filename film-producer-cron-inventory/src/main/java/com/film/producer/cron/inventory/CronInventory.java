package com.film.producer.cron.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CronInventory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronInventory.class);

    public static void main(final String[] args){
        ClassPathXmlApplicationContext context = null;
        try {
            LOGGER.info("Starting application cron...");
            context = new ClassPathXmlApplicationContext("classpath:cron-inventory-scheduler.xml");
            LOGGER.info("Running application cron ...");
            context.registerShutdownHook();
            while (true) {
                Thread.sleep(5000);
            }
        } catch (final Exception e) {
            LOGGER.error("Exception in CronInventory", e);
            if (context != null) {
                context.close();
            }
        }
    }
}
