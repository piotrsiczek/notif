package com.spiczek.notif.server.notification.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Piotr Siczek
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AccelData {

    @Id
    private String id;
    private int x;
    private int y;
    private int z;
    private String time;
}
