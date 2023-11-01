package hr.fer.tel.rassus.examples.beans;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Sensor {

    private Integer id;
    private Double longitude;
    private Double latitude;
    private IpPort ipPort;
    private List<Reading> readings;
    private final LocalDateTime startupTime;

    public Sensor(String ip, Integer port) throws FileNotFoundException {
        this.longitude = 15.87 + (16 - 15.87) * Math.random();
        this.latitude = 45.75 + (45.85 - 45.75) * Math.random();
        this.ipPort = new IpPort(ip, port);
        this.startupTime = LocalDateTime.now();
        this.readings = new CsvToBeanBuilder(new FileReader("readings.csv"))
                .withType(Reading.class).build().parse();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public IpPort getIpPort() {
        return ipPort;
    }

    public void setIpPort(IpPort ipPort) {
        this.ipPort = ipPort;
    }

    public Reading getReading() {
        return readings.get((int) ChronoUnit.SECONDS.between(startupTime, LocalDateTime.now()) % 100 + 1);
    }
}
