package hr.fer.tel.rassus.examples;

import hr.fer.tel.rassus.examples.beans.IpPort;
import hr.fer.tel.rassus.examples.dto.ReadingCreate;
import hr.fer.tel.rassus.examples.dto.SensorCreate;

public interface ServerClient {


    Integer register(SensorCreate sensorCreate);

    IpPort getNearestNeighbour(Integer sensorId);

    void storeReading(Integer sensorId, ReadingCreate readingCreate);
}
