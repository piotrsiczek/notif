package com.spiczek.notif.server.notification;

import com.spiczek.notif.server.notification.model.AccelData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

/**
 * @author Piotr Siczek
 */
@Slf4j
@Service
@AllArgsConstructor
public class NotifService {

    private ReactiveMongoTemplate reactiveMongoTemplate;

    @PostConstruct
    public void init() {
        log.info("************************ init ***********************");
        WebClient client = WebClient.builder().build();
        client.get().uri("http://localhost:9999/accel")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(AccelData.class).subscribe(e -> reactiveMongoTemplate.save(e).block());
    }
}
