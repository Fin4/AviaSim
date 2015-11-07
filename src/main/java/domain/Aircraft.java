package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jgroups.JChannel;
import org.jgroups.Message;
import typeAdapters.AircraftAdapter;
import typeAdapters.HelicopterAdapter;
import typeAdapters.PlaneAdapter;

import java.io.Serializable;

public abstract class Aircraft implements Flyable, Runnable, Serializable {

    protected int number;
    protected float latitude;
    protected float longitude;
    protected float altitude;
    protected float course;

    protected transient boolean bFly;

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

        return number == aircraft.number;

    }

    @Override
    public int hashCode() {
        return number;
    }

    public Aircraft(int number) {
        this.number = number;
        this.course = 0;
        this.altitude = 0;
        this.longitude = 0;
        this.latitude = 0;
    }

    public void run() {
        try {
            JChannel channel = new JChannel();
            channel.connect("AirCluster");
            Gson gson = new GsonBuilder().
                    registerTypeAdapter(Plane.class, new PlaneAdapter()).
                    registerTypeAdapter(Helicopter.class, new HelicopterAdapter()).
                    /*registerTypeAdapter(Aircraft.class, new AircraftAdapter()).*/
                    create();
            while (bFly) {
                latitude += (float) Math.random() * 10;
                longitude += (float) Math.random() * 10;
                altitude = (float) Math.random() * 1000;

                String msg = gson.toJson(this);
                //System.out.println(msg);
                channel.send(new Message(null, null, msg));
                Thread.sleep(4000);
            }
            channel.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fly() {
        new Thread(this).start();
        bFly = true;
    }

    public void landing() {
        bFly = false;
    }

    public Aircraft() {
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "number=" + number +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", course=" + course +
                '}';
    }
}
