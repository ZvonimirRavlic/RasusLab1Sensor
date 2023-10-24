package hr.fer.tel.rassus.sensor.sensorapplication.beans;

import java.util.Random;

public class Sensor {

    private Double longitude;
    private Double latitude;
    private String ip;
    private Integer port;

    public Sensor(String ip, Integer port) {
        this.longitude = 15.87 + (16 - 15.87) * Math.random();
        this.latitude = 45.75 + (45.85 - 45.75) * Math.random();
        this.ip = ip;
        this.port = port;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
