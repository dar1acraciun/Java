import java.util.List;

public class Airport {
    private String airportName;
    private List<Runway> runways;
    private List<AirCraft> airCrafts;


    public Airport(String airportName, List<Runway> runways, List<AirCraft> airCrafts) {
        this.airportName = airportName;
        this.runways = runways;
        this.airCrafts = airCrafts;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public List<Runway> getRunways() {
        return runways;
    }

    public void setRunways(List<Runway> runways) {
        this.runways = runways;
    }

    public List<AirCraft> getAirCrafts() {
        return airCrafts;
    }

    public void setAirCrafts(List<AirCraft> airCrafts) {
        this.airCrafts = airCrafts;
    }
}

