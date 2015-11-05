package domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class DispatchCenter{

    private Set<Aircraft> aircraftSet = new HashSet<Aircraft>();

    private static Logger log = Logger.getLogger(DispatchCenter.class.getName());

    public DispatchCenter() {
        try {
            JChannel channel = new JChannel();
            channel.setReceiver(new ReceiverAdapter(){

                @Override
                public void receive(Message msg) {
                    Gson gson = new GsonBuilder().create();
                    Aircraft aircraft = gson.fromJson(msg.getObject().toString(), Aircraft.class);
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
                for (Aircraft a : aircraftSet) {
                    log.info(a.toString());
                }
            }
        }, 10*1000, 10*1000);
    }
}
