package hr.fer.tel.rassus.examples;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SensorClient {

    private static final Logger logger = Logger.getLogger(SensorClient.class.getName());
    private final ManagedChannel channel;
    private final Integer port;
    private final ReadingServiceGrpc.ReadingServiceBlockingStub readingServiceBlockingStub;

    public SensorClient(String host, int port) {
        this.port = port;
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.readingServiceBlockingStub = ReadingServiceGrpc.newBlockingStub(channel);

    }

    public void stop() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public ReadingResp requestReading() {
        Empty request = Empty.newBuilder().build();
        logger.info("Requested new reading from neighbour(port: " + port + ")");
        try {
            final ReadingResp readingResp = readingServiceBlockingStub.requestReading(request);
            logger.info("Received response from neighbour(port: " + port + ")");
            return readingResp;
        } catch (StatusRuntimeException statusRuntimeException) {
            logger.info("Didn't receive response from neighbour(port: " + port + ")");
            return null;
        }

    }
}
