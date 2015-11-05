import domain.DispatchCenter;
import domain.Helicopter;
import domain.Plane;


public class Main {

    public static void main(String[] args) {
        new Helicopter("MI-17", 2719).fly();
        new Helicopter("MI-171", 2209).fly();
        new Helicopter("MI-2", 2367).fly();

        new Plane("L39", 7056).fly();
        new Plane("A300", 7537).fly();
        new Plane("B777", 7426).fly();

        new DispatchCenter();
    }
}