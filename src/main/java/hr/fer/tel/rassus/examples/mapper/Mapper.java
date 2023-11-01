package hr.fer.tel.rassus.examples.mapper;

import hr.fer.tel.rassus.examples.beans.Reading;
import hr.fer.tel.rassus.examples.beans.Sensor;
import hr.fer.tel.rassus.examples.dto.ReadingCreate;
import hr.fer.tel.rassus.examples.dto.SensorCreate;

public class Mapper {


    public static SensorCreate toSensorCreate(Sensor sensor) {
        final SensorCreate sensorCreate = new SensorCreate();
        sensorCreate.setLatitude(sensor.getLatitude());
        sensorCreate.setLongitude(sensor.getLongitude());
        sensorCreate.setIp(sensor.getIpPort().getIp());
        sensorCreate.setPort(sensor.getIpPort().getPort());
        return sensorCreate;
    }

    public static ReadingCreate toReadingCreate(Reading currentReading) {
        final ReadingCreate reading = new ReadingCreate();
        reading.setTemperature(currentReading.getTemperature());
        reading.setPressure(currentReading.getPressure());
        reading.setHumidity(currentReading.getHumidity());
        reading.setCo(currentReading.getCo());
        reading.setSo2(currentReading.getSo2());
        reading.setNo2(currentReading.getNo2());
        return reading;
    }
}
