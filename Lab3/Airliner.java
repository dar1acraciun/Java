public class Airliner extends AirCraft implements PassengerCapable{
    private int passengersCapacity;
    private int wingSpan;

    public Airliner(String name, String model, String color, int numPassengers, int wingSpan) {
        super(name, model, color);
        this.passengersCapacity = numPassengers;
        this.wingSpan = wingSpan;

    }

    public void setNumPassengers(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    @Override
    public String toString() {
        return "Airliner{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public int getPassengerCapacity() {
        return passengersCapacity;
    }

    public int getWingSpan() {
        return wingSpan;
    }

}
