package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import typeAdapters.AircraftAdapter;
import typeAdapters.HelicopterAdapter;
import typeAdapters.PlaneAdapter;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class DispatchCenter{

    private Set<Flyable> aircraftSet = new HashSet<Flyable>();

    private static Logger log = Logger.getLogger(DispatchCenter.class.getName());

    public DispatchCenter() {
        try {
            JChannel channel = new JChannel();
            channel.setReceiver(new ReceiverAdapter(){

                @Override
                public void receive(Message msg) {
                    Gson gson = new GsonBuilder().
                            registerTypeAdapter(Plane.class, new PlaneAdapter()).
                            registerTypeAdapter(Helicopter.class, new HelicopterAdapter()).
                            registerTypeAdapter(Aircraft.class, new AircraftAdapter()).
                            create();
                    Flyable aircraft = gson.fromJson(msg.getObject().toString(), Aircraft.class);
                    if (!aircraftSet.contains(aircraft)) {
                        aircraftSet.add(aircraft);
                    }
                }
            });

            channel.connect("AirCluster");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Flyable a : aircraftSet) {
                    Aircraft aircraft = (Aircraft)a;
                    //log.info(aircraft.toString());
                }
                System.out.println(aircraftSet);
            }
        }, 1000, 10*1000);
    }
}
