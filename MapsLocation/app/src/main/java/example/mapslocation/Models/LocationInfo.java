package example.mapslocation.Models;

import java.util.Date;

/**
 * Created by frost on 06.12.2017.
 */

public class LocationInfo {
    private Double latitude;
    private Double longitude;
    private Date date;
    private float speed;
    private Double altitude;

    public LocationInfo(){}

    public LocationInfo(Double latitude,Double longitude,Date date,float speed,Double altitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.speed = speed;
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "Coordinates:\n lat = " + latitude +
                ",\n lon = " + longitude +
                ",\n time = " + date +
                ",\n speed = " + speed +
                ",\n high = " + altitude;
    }
}
