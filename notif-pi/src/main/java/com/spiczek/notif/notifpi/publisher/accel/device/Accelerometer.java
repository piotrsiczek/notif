package com.spiczek.notif.notifpi.publisher.accel.device;

import com.spiczek.notif.notifpi.publisher.model.AccelData;

/**
 * @author Piotr Siczek
 */
public interface Accelerometer {
    AccelData getReading() throws Exception;
}
