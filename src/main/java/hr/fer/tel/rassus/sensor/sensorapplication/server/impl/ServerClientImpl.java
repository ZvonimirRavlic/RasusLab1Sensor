package hr.fer.tel.rassus.sensor.sensorapplication.server.impl;

import hr.fer.tel.rassus.sensor.sensorapplication.beans.Sensor;
import hr.fer.tel.rassus.sensor.sensorapplication.server.ServerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.logging.Logger;

public class ServerClientImpl implements ServerClient {

    private static final Logger LOG = Logger.getLogger(ServerClientImpl.class.getName());
    private String baseURL;
    private RestTemplate restTemplate;

    public ServerClientImpl(String url) {
        this.baseURL = url;
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public Integer register(Sensor sensor) {
        final ResponseEntity<Void> response = restTemplate.postForEntity(baseURL + "/sensors", sensor, Void.class);
        final String locationPath = Objects.requireNonNull(response.getHeaders().getLocation()).getRawPath();
        return Integer.parseInt(locationPath.substring(locationPath.lastIndexOf('/') + 1));
    }
}
