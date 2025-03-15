public class AirCraft {
    protected String name;
    protected String model;
    protected String color;

    public AirCraft(String name, String model, String color) {
        this.name = name;
        this.model = model;
        this.color = color;

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
