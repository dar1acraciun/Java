import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bonus {
    private final Airport airport;
    private final List<Runway> runways;
    private List<Flight> flights;

    public Bonus(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
        this.runways = airport.getRunways();
    }

    private void sortingFlights() {
        flights.sort((f1, f2) -> Flight.compareByStartTime(f1.getStartTimeArrival(), f2.getStartTimeArrival()));
    }


    private Boolean isRunwayAvailable(Runway runway, Flight flight) {

        if (runway.getFlightsOnRunway() == null) {
            return true;
        }
        for (Flight flightOnRunway : runway.getFlightsOnRunway()) {
            if (flight.getStartTimeArrival().isBefore(flightOnRunway.getEndTimeArrival()) && flight.getEndTimeArrival().isAfter(flightOnRunway.getStartTimeArrival())) {
                return false;
            }
        }
        return true;
    }

    private Boolean isBalanced() {
        for (int i = 0; i < runways.size(); i++) {
            for (int j = i + 1; j < runways.size(); j++) {
                int diff = Math.abs(runways.get(i).getNumberOfFlightsOnRunway() - runways.get(j).getNumberOfFlightsOnRunway());
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }


    private Boolean AssignRunwayAvailable(Map<Flight, Runway> assignRunway, Flight flight) {

        for (Runway runway : runways) {
            List<Flight> newFlights;
            List<Flight> oldFlights = runway.getFlightsOnRunway() != null ? new ArrayList<>(runway.getFlightsOnRunway()) : null;

            if (isRunwayAvailable(runway, flight)) {
                newFlights = runway.getFlightsOnRunway() != null ? new ArrayList<>(runway.getFlightsOnRunway()) : new ArrayList<>();

                newFlights.add(flight);
                runway.setFlightsOnRunway(newFlights);

                if (isBalanced()) {
                    assignRunway.put(flight, runway);
                    return true;
                } else {
                    runway.setFlightsOnRunway(oldFlights);
                }
            }
        }
        return false;
    }


    public void schedueleFlights() {
        sortingFlights();
        boolean findScheduele = true;
        Map<Flight, Runway> assignRunway = new HashMap<>();
        for (Flight flight : flights) {
            System.out.println(flight.toString() + '\n');
            if (!AssignRunwayAvailable(assignRunway, flight)) {
                findScheduele = false;
                break;
            }
        }
        if (findScheduele) {
            printSolution(assignRunway);
        } else {
            System.out.println("No scheduele flights found");
        }

    }
    private void printSolution(Map<Flight, Runway> assignRunway) {
        for (Flight flight : assignRunway.keySet()) {
            System.out.println(flight.getAirCraft().toString() + " " + assignRunway.get(flight).toString() + "\n");
        }
    }

}
