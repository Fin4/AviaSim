package domain;

import domain.type.PlaneType;

public class Plane extends Aircraft{

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
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", course=" + course +
                '}';
    }
}
