package com.spiczek.notif.notifpi.publisher.accel.device;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.spiczek.notif.notifpi.publisher.model.AccelData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Piotr Siczek
 */
@Profile("pi")
@Component
public class Lis3dh implements Accelerometer {

    private I2CBus bus;
    private I2CDevice device;

    public Lis3dh() throws Exception {
        // Create I2C bus
        bus = I2CFactory.getInstance(I2CBus.BUS_1);
        // Get I2C device, LIS3DHTR I2C address is 0x18(24)
        device = bus.getDevice(0x18);

        // Select control register1
        // X, Y and Z axis enabled, power on mode, data rate o/p 10Hz
        device.write(0x20, (byte)0x27);
        // Select control register4
        // Full scale +/- 2g, continuous update
        device.write(0x23, (byte)0x00);
        Thread.sleep(300);
    }

    @Override
    public AccelData getReading() throws Exception {
        // Read 6 bytes of data
        // xAccl lsb, xAccl msb, yAccl lsb, yAccl msb, zAccl lsb, zAccl msb
        byte[] data = new byte[6];
        data[0] = (byte)device.read(0x28);
        data[1] = (byte)device.read(0x29);
        data[2] = (byte)device.read(0x2A);
        data[3] = (byte)device.read(0x2B);
        data[4] = (byte)device.read(0x2C);
        data[5] = (byte)device.read(0x2D);

        // Convert the data
        int xAccl = ((data[1] & 0xFF) * 256 + (data[0] & 0xFF));
        if(xAccl > 32767)
        {
            xAccl -= 65536;
        }

        int yAccl = ((data[3] & 0xFF) * 256 + (data[2] & 0xFF));
        if(yAccl > 32767)
        {
            yAccl -= 65536;
        }

        int zAccl = ((data[5] & 0xFF) * 256 + (data[4] & 0xFF));
        if(zAccl > 32767)
        {
            zAccl -= 65536;
        }

        return AccelData.builder().x(xAccl).y(yAccl).z(zAccl).build();
    }
}