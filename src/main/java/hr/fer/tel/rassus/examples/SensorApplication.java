package hr.fer.tel.rassus.examples;

import hr.fer.tel.rassus.examples.beans.IpPort;
import hr.fer.tel.rassus.examples.beans.Reading;
import hr.fer.tel.rassus.examples.beans.Sensor;
import hr.fer.tel.rassus.examples.mapper.Mapper;

import java.io.IOException;
import java.util.logging.Logger;

public class SensorApplication {

    private static final Logger logger = Logger.getLogger(SensorApplication.class.getName());
    public static Reading currentReading = new Reading();
    public static ServerClient serverClient = new ServerClientImpl();
    public static SensorClient sensorClient;
    public static Sensor sensor;

    public static void main(String[] args) throws IOException, InterruptedException {
        sensor = new Sensor(args[0], Integer.valueOf(args[1]));
        sensor.setId(serverClient.register(Mapper.toSensorCreate(sensor)));
        logger.info("Registered sensor");
        final SensorServer server = new SensorServer(new SensorService(), sensor.getIpPort().getPort());
        logger.info("Started own grpc server");
        server.start();
        while (1 == 1) {
            currentReading = sensor.getReading();
            logger.info("Read new reading");
            ReadingResp readingResp = fetchReading();
            currentReading.calibrate(readingResp);
            logger.info("Calibrated new reading");
            serverClient.storeReading(sensor.getId(), Mapper.toReadingCreate(currentReading));
            logger.info("Stored new reading");
            Thread.sleep(10000);
        }

    }

    private static ReadingResp fetchReading() {
        if (sensorClient == null) {
            final IpPort nearestNeighbour = serverClient.getNearestNeighbour(sensor.getId());
            if (nearestNeighbour != null) {
                sensorClient = new SensorClient(nearestNeighbour.getIp(), nearestNeighbour.getPort());
            } else {
                logger.info("Didn't find neighbouring sensor");
                return null;
            }
        }
        return sensorClient.requestReading();
    }
}
