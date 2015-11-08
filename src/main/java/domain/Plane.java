package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.type.PlaneType;
import org.jgroups.JChannel;
import org.jgroups.Message;
import typeAdapters.PlaneAdapter;

public class Plane extends Aircraft {

    private PlaneType type;

    public Plane(PlaneType type, int number) {
        super(number);
        this.type = type;
    }

    public PlaneType getType() {
        return type;
    }

    public void setType(PlaneType type) {
        this.type = type;
    }

    public Plane() {}

    @Override
    public String toString() {
        return "Plane{" +
                "type=" + type +
                ", number=" + number +
                ", latitude=" + coords.getLatitude() +
                ", longitude=" + coords.getLongitude() +
                ", altitude=" + coords.getAltitude() +
                ", course=" + coords.getCourse() +
                '}';
    }

    public void run() {
        try {
            JChannel channel = new JChannel();
            channel.connect("AirCluster");
            Gson gson = new GsonBuilder().
                    registerTypeAdapter(Plane.class, new PlaneAdapter()).
                    create();
            while (bFly) {
                coords.setLatitude(coords.getLatitude() + (float) Math.random() * 10);
                coords.setLongitude(coords.getLongitude() + (float) Math.random() * 10);
                coords.setAltitude(coords.getAltitude() + (float) Math.random() * 1000);
                coords.setCourse(coords.getCourse() + (float) Math.random() * 1000);

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
}
