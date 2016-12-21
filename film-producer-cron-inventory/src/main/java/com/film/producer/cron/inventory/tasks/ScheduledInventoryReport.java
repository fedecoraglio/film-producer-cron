package com.film.producer.cron.inventory.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledInventoryReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledInventoryReport.class);

    @Autowired
    private ApplicationContext context;

    @Scheduled(cron = "${cron.inventory.scheduled.time}")
    //@Scheduled(fixedDelayString = "${cron.inventory.scheduled.time.fix}")
    public void scheduledUserProfileCreatedFromJanuaryToJuly() {
        LOGGER.info("Starting getting film inventory...");
        try {
                final Job shiftConfirmationPending = context.getBean("filmInventoryJob", Job.class);
                final JobLauncher jobLauncher = context.getBean("asyncSchedulerJobLauncher", JobLauncher.class);
                final JobParametersBuilder builder = new JobParametersBuilder();
                builder.addLong("id", new Date().getTime());
                final JobExecution jobExecution = jobLauncher.run(shiftConfirmationPending, builder.toJobParameters());
                LOGGER.info("Created Execution ", jobExecution.getCreateTime());
        } catch (final Exception e) {
            LOGGER.error("Error occurred at the moment of getting film inventory", e);
        }
        LOGGER.info("Starting getting film inventory completed.");
    }
}
