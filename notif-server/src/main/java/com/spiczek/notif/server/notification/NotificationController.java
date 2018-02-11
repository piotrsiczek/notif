package com.spiczek.notif.server.notification;

import com.spiczek.notif.server.notification.model.AccelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author Piotr Siczek
 */
@Slf4j
@RestController
public class NotificationController {

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccelData> getAccelData() {
        WebClient client = WebClient.builder().build();
        return client.get().uri("http://localhost:9999/accel")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(AccelData.class);
    }

}
