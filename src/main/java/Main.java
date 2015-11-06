import domain.DispatchCenter;
import domain.Helicopter;
import domain.Plane;
import domain.type.HelicopterType;
import domain.type.PlaneType;


public class Main {

    public static void main(String[] args) {
        new Helicopter(HelicopterType.MI_17, 2719).fly();
        new Helicopter(HelicopterType.MI_171, 2209).fly();
        new Helicopter(HelicopterType.MI_2, 2367).fly();

        new Plane(PlaneType.L39, 7056).fly();
        new Plane(PlaneType.A300, 7537).fly();
        new Plane(PlaneType.B777, 7426).fly();

        new DispatchCenter();
    }
}