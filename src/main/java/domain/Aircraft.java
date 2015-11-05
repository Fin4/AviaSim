package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jgroups.JChannel;
import org.jgroups.Message;

import java.io.Serializable;

public class Aircraft implements Flyable, Runnable, Serializable{

    private String type;
    private int number;
    private float latitude;
    private float longitude;
    private float altitude;
    private float course;

    private boolean bFly;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getCourse() {
        return course;
    }

    public void setCourse(float course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;

        Aircraft aircraft = (Aircraft) o;

        if (getNumber() != aircraft.getNumber()) return false;
        if (Float.compare(aircraft.getLongitude(), getLongitude()) != 0) return false;
        return getType().equals(aircraft.getType());

    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getNumber();
        result = 31 * result + (getLongitude() != +0.0f ? Float.floatToIntBits(getLongitude()) : 0);
        return result;
    }

    public Aircraft(String type, int number) {
        this.type = type;
        this.number = number;
        this.course = 0;
        this.altitude = 0;
        this.longitude = 0;
        this.latitude = 0;
    }

    public Aircraft() {
    }

    public void fly() {
        new Thread(this).start();
        bFly = true;
    }

    public void landing() {
        bFly = false;
    }

    public boolean isbFly() {
        return bFly;
    }

    public void run() {
        try {
            JChannel channel = new JChannel();
            channel.connect("AirCluster");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            while (bFly) {
                latitude += (float) Math.random() * 10;
                longitude += (float) Math.random() * 10;
                altitude = (float) Math.random() * 1000;

                String msg = gson.toJson(this);
                channel.send(new Message(null, null, msg));
                Thread.sleep(4000);
            }
            channel.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", course=" + course +
                '}';
    }
}
