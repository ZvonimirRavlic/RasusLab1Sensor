package hr.fer.tel.rassus.examples;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SensorServer {

    private static final Logger logger = Logger.getLogger(SensorServer.class.getName());
    private Server server;
    private final SensorService service;
    private final int port;

    public SensorServer(SensorService service, int port) {
        this.service = service;
        this.port = port;
    }

    public void start() throws IOException {
        // Register the service
        server = ServerBuilder.forPort(port)
                .addService(service)
                .build()
                .start();
        logger.info("Server started on " + port);

        //  Clean shutdown of server in case of JVM shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.warning("Shutting down gRPC server since JVM is shutting down");
            try {
                SensorServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            logger.info("Server shut down");
        }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
}
