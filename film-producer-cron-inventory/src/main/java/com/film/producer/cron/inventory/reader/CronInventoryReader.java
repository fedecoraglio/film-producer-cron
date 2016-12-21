package com.film.producer.cron.inventory.reader;

import com.film.producer.core.dao.FilmReportDAO;
import com.film.producer.core.model.data.FilmInventoryData;
import com.film.producer.cron.inventory.writer.CronInventoryWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
@Scope("step")
public class CronInventoryReader implements ItemStreamReader<FilmInventoryData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronInventoryWriter.class);

    @Autowired
    private FilmReportDAO filmJdbcDAO;

    private BlockingQueue<FilmInventoryData> filmReport;

    public FilmInventoryData read() throws Exception {
        if(filmReport != null) {
            return filmReport.poll();
        }
        return null;
    }

    public void open(ExecutionContext executionContext) throws ItemStreamException {
        final List<FilmInventoryData> filmInventoryDataList = filmJdbcDAO.getInventoryData();
        if(filmInventoryDataList != null && filmInventoryDataList.size() > 0) {
            LOGGER.info("Reading film inventory : " + filmInventoryDataList.size());
            filmReport = new ArrayBlockingQueue<FilmInventoryData>(filmInventoryDataList.size());
            filmReport.addAll(filmInventoryDataList);
        }
    }

    public void update(final ExecutionContext executionContext) throws ItemStreamException {}

    public void close() throws ItemStreamException {}
}
