package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.type.HelicopterType;
import org.jgroups.JChannel;
import org.jgroups.Message;
import typeAdapters.HelicopterAdapter;

public class Helicopter extends Aircraft {

    private HelicopterType type;

    public HelicopterType getType() {
        return type;
    }

    public void setType(HelicopterType type) {
        this.type = type;
    }

    public Helicopter(HelicopterType type, int number) {
        super(number);
        this.type = type;
    }

    public Helicopter() {}

    @Override
    public String toString() {
        return "Helicopter{" +
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
                    registerTypeAdapter(Helicopter.class, new HelicopterAdapter()).
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
