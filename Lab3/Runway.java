import java.util.List;

public class Runway {
    String name;
    private List<Flight> flightsOnRunway;

    public Runway(String name, List<Flight> flightsOnRunway) {
        this.name = name;
        this.flightsOnRunway = flightsOnRunway;
    }

    public List<Flight> getFlightsOnRunway() {
        return flightsOnRunway;
    }

    public void setFlightsOnRunway(List<Flight> flightsOnRunway) {
        this.flightsOnRunway = flightsOnRunway;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getNumberOfFlightsOnRunway() {
        if(flightsOnRunway == null) {
            return 0;
        }
        else return flightsOnRunway.size();
    }
    @Override
    public String toString() {
        return "Runway{" +
                "name='" + name + '\'' +
                '}';
    }
}
