public class Freighter extends AirCraft implements CargoCapable {
    private int maxPlayLoad;
    private int wingSpan;

    public Freighter(String name, String model, String color, int maxPlayLoad, int wingSpan) {
        super(name, model, color);
        this.maxPlayLoad = maxPlayLoad;
        this.wingSpan = wingSpan;
    }

    public int getMaxPlayLoad() {
        return maxPlayLoad;
    }

    public void setMaxPlayLoad(int maxPlayLoad) {
        this.maxPlayLoad = maxPlayLoad;
    }

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    @Override
    public String toString() {
        return "Freighter{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
