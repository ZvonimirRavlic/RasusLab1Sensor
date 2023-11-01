package hr.fer.tel.rassus.examples.beans;

import com.opencsv.bean.CsvBindByName;
import hr.fer.tel.rassus.examples.ReadingResp;

public class Reading {

    @CsvBindByName(column = "Temperature")
    private Integer temperature;

    @CsvBindByName(column = "Pressure")
    private Integer pressure;

    @CsvBindByName(column = "Humidity")
    private Integer humidity;

    @CsvBindByName(column = "CO")
    private Integer co;

    @CsvBindByName(column = "NO2")
    private Integer no2;

    @CsvBindByName(column = "SO2")
    private Integer so2;

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
    }

    public Integer getNo2() {
        return no2;
    }

    public void setNo2(Integer no2) {
        this.no2 = no2;
    }

    public Integer getSo2() {
        return so2;
    }

    public void setSo2(Integer so2) {
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

    private Integer average(Integer value1, Integer value2) {
        if (value1 == null && value2 == null) return null;
        if (value1 == null) return value2;
        if (value2 == null) return value1;
        return (value1 + value2) / 2;
    }
}

