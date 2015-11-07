package domain;

import domain.type.HelicopterType;

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
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", course=" + course +
                '}';
    }
}
