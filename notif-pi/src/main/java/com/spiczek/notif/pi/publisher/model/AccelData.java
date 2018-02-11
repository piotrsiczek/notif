package com.spiczek.notif.pi.publisher.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.Value;

/**
 * @author Piotr Siczek
 */
@Data
@Value
@Builder
@ToString
public class AccelData {
    private int x;
    private int y;
    private int z;
    private String time;
}
