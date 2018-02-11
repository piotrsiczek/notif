package com.spiczek.notif.panel.controllers;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.spiczek.notif.panel.model.AccelData;
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
    public Flux<AccelData> getAccelData() {
        MongoCollection<Document> collection = reactiveMongoTemplate.getCollection("accelData");
        return Flux.from(collection.watch().fullDocument(FullDocument.UPDATE_LOOKUP)).map(ChangeStreamDocument::getFullDocument)
                .map(this::mapToAccelData);
    }

    private AccelData mapToAccelData(Document document) {
        return new AccelData(document.getInteger("x"), document.getInteger("y"), document.getInteger("z"), document.getString("time"));
    }
}
