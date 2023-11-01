package hr.fer.tel.rassus.examples;

import hr.fer.tel.rassus.examples.beans.IpPort;
import hr.fer.tel.rassus.examples.dto.ReadingCreate;
import hr.fer.tel.rassus.examples.dto.SensorCreate;
import hr.fer.tel.rassus.examples.dto.SensorResp;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ServerClientImpl implements ServerClient {

    private String baseURL;
    private RestTemplate restTemplate;

    public ServerClientImpl() {
        this.baseURL = "http://localhost:8090";
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public Integer register(SensorCreate sensorCreate) {
        ResponseEntity<Void> response = this.restTemplate.postForEntity(this.baseURL + "/sensors", sensorCreate, Void.class);
        if (response.getStatusCode().isError()) {
            System.exit(0);
        }
        String locationPath = response.getHeaders().getLocation().getRawPath();
        return Integer.parseInt(locationPath.substring(locationPath.lastIndexOf("/") + 1));
    }

    @Override
    public IpPort getNearestNeighbour(Integer sensorId) {
        SensorResp resp = this.restTemplate.getForObject(this.baseURL + "/sensors/" + sensorId + "/nearestNeighbour", SensorResp.class);
        if (resp == null) {
            return null;
        }
        return new IpPort(resp.getIp(), resp.getPort());
    }

    @Override
    public void storeReading(Integer sensorId, ReadingCreate readingCreate) {
        this.restTemplate.postForEntity(this.baseURL + "/reading/sensor/" + sensorId, readingCreate, Void.class);
    }
}
