package com.film.producer.cron.actor.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.film.producer.cron.actor.response.ActorResponse;

@Component
public class ActorTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorTask.class);

    @Value("${film.producer.cron.actor.rest.server.url}")
    private String baseRestServerUrl;

    @Scheduled(fixedDelayString = "${film.producer.cron.actor.read.scheduled}")
    public void readAllActors() {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ActorResponse[]> responseEntity =
                restTemplate.getForEntity(baseRestServerUrl+"/v1/actors", ActorResponse[].class);
        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null &&
                responseEntity.getBody().length > 0) {
            for (final ActorResponse actorResponse : responseEntity.getBody()) {
                LOGGER.info("{}", actorResponse);
            }
        }
    }
}
