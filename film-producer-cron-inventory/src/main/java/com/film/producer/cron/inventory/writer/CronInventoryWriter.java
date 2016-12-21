package com.film.producer.cron.inventory.writer;

import com.film.producer.core.model.data.FilmInventoryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("step")
public class CronInventoryWriter implements ItemWriter<FilmInventoryData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronInventoryWriter.class);

    public void write(final List<? extends FilmInventoryData> list) throws Exception {
        for(final FilmInventoryData film: list) {
            LOGGER.info("Film inventory: " + film.toString());
        }
    }
}
