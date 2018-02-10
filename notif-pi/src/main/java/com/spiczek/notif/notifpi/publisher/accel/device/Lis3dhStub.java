package com.spiczek.notif.notifpi.publisher.accel.device;

import com.spiczek.notif.notifpi.publisher.model.AccelData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Piotr Siczek
 */
@Profile("local")
@Component
public class Lis3dhStub implements Accelerometer {

    @Override
    public AccelData getReading() throws Exception {
        return AccelData.builder().x(1).y(2).z(3).build();
    }
}