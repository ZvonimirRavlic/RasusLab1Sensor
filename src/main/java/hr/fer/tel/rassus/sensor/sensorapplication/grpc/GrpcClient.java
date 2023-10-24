package hr.fer.tel.rassus.sensor.sensorapplication.grpc;

import io.grpc.Server;

import java.util.logging.Logger;

public class GrpcClient {

    private static final Logger LOG = Logger.getLogger(GrpcClient.class.getName());

    private Server server;
    private final Integer port = 8080;
}
