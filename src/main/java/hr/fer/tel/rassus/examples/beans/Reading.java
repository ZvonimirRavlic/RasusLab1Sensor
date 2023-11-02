package hr.fer.tel.rassus.examples.beans;

import com.opencsv.bean.CsvBindByName;
import hr.fer.tel.rassus.examples.ReadingResp;

public class Reading {

    @CsvBindByName(column = "Temperature")
    private Double temperature;

    @CsvBindByName(column = "Pressure")
    private Double pressure;

    @CsvBindByName(column = "Humidity")
    private Double humidity;

    @CsvBindByName(column = "CO")
    private Double co;

    @CsvBindByName(column = "NO2")
    private Double no2;

    @CsvBindByName(column = "SO2")
    private Double so2;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public void calibrate(ReadingResp resp) {
        if (resp == null) {
            return;
        }
        setTemperature(average(getTemperature(), resp.getTemperature()));
        setPressure(average(getPressure(), resp.getPressure()));
        setHumidity(average(getHumidity(), resp.getHumidity()));
        setCo(average(getCo(), resp.getCo()));
        setNo2(average(getNo2(), resp.getNo2()));
        setSo2(average(getSo2(), resp.getSo2()));
    }

    private Double average(Double value1, Double value2) {
        if ((value1 == null || value1 == 0) && (value2 == null || value2 == 0)) return null;
        if ((value1 == null || value1 == 0)) return value2;
        if ((value2 == null || value2 == 0)) return value1;
        return (value1 + value2) / 2;
    }
}

