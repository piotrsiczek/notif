package com.spiczek.notif.notifpi.publisher.accel;

import com.spiczek.notif.notifpi.publisher.accel.device.Accelerometer;
import com.spiczek.notif.notifpi.publisher.model.AccelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Piotr Siczek
 */
@Slf4j
@RestController
public class AccelerometerPublisher {

    private Accelerometer accl;

    public AccelerometerPublisher(Accelerometer accl) {
        this.accl = accl;
    }

    @GetMapping(value = "/accel", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccelData> asdf() {
        Flux<Long> interval = Flux.interval(Duration.ofMillis(20));
        Flux<AccelData> accelInfiniteFlux = Flux.fromStream(Stream.generate(this::getAccelData));
        return Flux.zip(interval, accelInfiniteFlux).map(Tuple2::getT2);
    }

    private AccelData getAccelData() {
        AccelData reading = null;
        try {
            reading = accl.getReading();
        } catch (Exception ex) {
            log.error(Arrays.toString(ex.getStackTrace()));
        }
        log.info(reading.pring());
        return reading;
    }
}
