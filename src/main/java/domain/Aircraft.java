package domain;

public abstract class Aircraft implements Flyable, Runnable {

    protected int number;
    protected Coords coords;
    protected transient boolean bFly;

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        coords = new Coords();
    }

    public void fly() {
        new Thread(this).start();
        bFly = true;
    }

    public void landing() {
        bFly = false;
    }

    public Aircraft() {
        coords = new Coords();
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "number=" + number +
                ", latitude=" + coords.getLatitude() +
                ", longitude=" + coords.getLongitude() +
                ", altitude=" + coords.getAltitude() +
                ", course=" + coords.getCourse() +
                '}';
    }
}
