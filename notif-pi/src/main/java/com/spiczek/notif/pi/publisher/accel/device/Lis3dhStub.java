package com.spiczek.notif.pi.publisher.accel.device;

import com.spiczek.notif.pi.publisher.model.AccelData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Piotr Siczek
 */
@Profile("local")
@Component
public class Lis3dhStub implements Accelerometer {

    @Override
    public AccelData getReading(String time) throws Exception {
        int x = random();
        int y = random();
        int z = random();
        return AccelData.builder().x(x).y(y).z(z).time(time).build();
    }

    private int random() {
        Random r = new Random();
        int Low = -100;
        int High = 101;
        return r.nextInt(High-Low) + Low;
    }
}