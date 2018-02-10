package com.spiczek.fotif.notif.notification;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.spiczek.fotif.notif.notification.model.AccelData;
import com.spiczek.fotif.notif.notification.model.Notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
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
@AllArgsConstructor
public class NotificationController {

    private ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChangeStreamDocument<org.bson.Document>> test() {
        MongoCollection<org.bson.Document> collection = reactiveMongoTemplate.getCollection("accelData");
        return Flux.from(collection.watch().fullDocument(FullDocument.UPDATE_LOOKUP));
    }

    @GetMapping(value = "/test")
    public void asdf() {
        Notification asdf = reactiveMongoTemplate.save(Notification.builder().value("asdf").build()).block();
        log.info("asdf");
    }

    @GetMapping(value = "/do", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccelData> asasdfdf() {
        WebClient client = WebClient.builder().build();
         return  client.get().uri("http://localhost:9999/accel")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(AccelData.class);
    }

}
