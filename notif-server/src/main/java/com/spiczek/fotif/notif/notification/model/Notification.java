package com.spiczek.fotif.notif.notification.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Piotr Siczek
 */
@Builder
@Value
@Document
public class Notification {

  @Id
  private String id;
  private String value;
}
