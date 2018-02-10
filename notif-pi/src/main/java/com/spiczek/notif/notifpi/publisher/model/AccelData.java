package com.spiczek.notif.notifpi.publisher.model;

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

    public String pring() {
        return "Acceleration in X-Axis : " + x + " Acceleration in Y-Axis : " + y + " Acceleration in Z-Axis : " + z;
    }
}
