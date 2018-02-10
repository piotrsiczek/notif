package com.spiczek.notif.notifpanel;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.reactivestreams.client.MongoCollection;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Piotr Siczek
 */
@RestController
@AllArgsConstructor
public class PanelController {

    private ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChangeStreamDocument<Document>> test() {
        MongoCollection<Document> collection = reactiveMongoTemplate.getCollection("accelData");
        return Flux.from(collection.watch().fullDocument(FullDocument.UPDATE_LOOKUP));
    }
}
