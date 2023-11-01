package hr.fer.tel.rassus.examples;

import java.util.logging.Logger;

import static hr.fer.tel.rassus.examples.SensorApplication.currentReading;

public class SensorService extends ReadingServiceGrpc.ReadingServiceImplBase {

    private static final Logger logger = Logger.getLogger(SensorService.class.getName());

    @Override
    public void requestReading(hr.fer.tel.rassus.examples.Empty request,
                               io.grpc.stub.StreamObserver<hr.fer.tel.rassus.examples.ReadingResp> responseObserver) {
        final ReadingResp.Builder builder = ReadingResp.newBuilder();

        if (currentReading.getTemperature() != null) {
            builder.setTemperature(currentReading.getTemperature());
        }
        if (currentReading.getPressure() != null) {
            builder.setPressure(currentReading.getPressure());
        }
        if (currentReading.getHumidity() != null) {
            builder.setHumidity(currentReading.getHumidity());
        }
        if (currentReading.getCo() != null) {
            builder.setCo(currentReading.getCo());
        }
        if (currentReading.getNo2() != null) {
            builder.setNo2(currentReading.getNo2());
        }
        if (currentReading.getSo2() != null) {
            builder.setSo2(currentReading.getSo2());
        }

        responseObserver.onNext(builder.build());

        logger.info("Responded to new request");
        responseObserver.onCompleted();
    }
}
