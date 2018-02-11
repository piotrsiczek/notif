package com.spiczek.notif.pi.publisher.accel.device;

import com.spiczek.notif.pi.publisher.model.AccelData;

/**
 * @author Piotr Siczek
 */
public interface Accelerometer {
    AccelData getReading(String time) throws Exception;
}
