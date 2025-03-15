public class Drone extends AirCraft {
    private int batteryLife;

    public Drone(String name, String model, String color, int batteryLife) {
        super(name, model, color);
        this.batteryLife = batteryLife;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }
}
