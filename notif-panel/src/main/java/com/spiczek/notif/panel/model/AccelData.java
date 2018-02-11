package com.spiczek.notif.panel.model;

import lombok.*;

/**
 * @author Piotr Siczek
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccelData {
    private Integer x;
    private Integer y;
    private Integer z;
    private String time;
}
