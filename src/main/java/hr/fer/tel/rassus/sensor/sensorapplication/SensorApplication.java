package hr.fer.tel.rassus.sensor.sensorapplication;

import hr.fer.tel.rassus.sensor.sensorapplication.beans.Reading;
import hr.fer.tel.rassus.sensor.sensorapplication.beans.Sensor;
import hr.fer.tel.rassus.sensor.sensorapplication.server.ServerClient;
import hr.fer.tel.rassus.sensor.sensorapplication.server.impl.ServerClientImpl;

public class SensorApplication {

    public static Reading READING;

    public static void main(String[] args) {
        final Sensor sensor = new Sensor(args[0], Integer.parseInt(args[1]));
        ServerClient serverClient = new ServerClientImpl("http://localhost:8090");
        Integer id = serverClient.register(sensor);



        System.out.println(id);
    }

}
