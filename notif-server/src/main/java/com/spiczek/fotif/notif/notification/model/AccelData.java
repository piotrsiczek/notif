package com.spiczek.fotif.notif.notification.model;

import lombok.*;

/**
 * @author Piotr Siczek
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccelData {
    private int x;
    private int y;
    private int z;

    public String pring() {
        return "Acceleration in X-Axis : " + x + " Acceleration in Y-Axis : " + y + " Acceleration in Z-Axis : " + z;
    }
}
