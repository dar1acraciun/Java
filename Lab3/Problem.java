import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
    private final Airport airport;
    private final List<Flight> flights;

    public Problem(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }

    private void addFlightToRunway(Map<Flight, Runway> solution, Flight flight, Runway runway) {
        solution.put(flight, runway);
        List<Flight> newFlightOnRunway;
        if (runway.getFlightsOnRunway() != null) newFlightOnRunway = runway.getFlightsOnRunway();
        else newFlightOnRunway = new ArrayList<Flight>();
        newFlightOnRunway.add(flight);
        runway.setFlightsOnRunway(newFlightOnRunway);


    }

    public void AssignRunway(Flight flight, Map<Flight, Runway> solution) {


        for (Runway runway : airport.getRunways()) {
            Boolean findRunway = true;
            if (runway.getFlightsOnRunway() != null) {
                for (Flight runwayFlight : runway.getFlightsOnRunway()) {

                    if (flight.getStartTimeArrival().isBefore(runwayFlight.getEndTimeArrival()) && flight.getEndTimeArrival().isAfter(runwayFlight.getStartTimeArrival())) {
                        findRunway = false;
                        break;
                    }
                }
            }

            if (findRunway) {

                addFlightToRunway(solution, flight, runway);
                break;
            }

        }
    }

    public void HomeworkSolution() {
        Map<Flight, Runway> solution = new HashMap<Flight, Runway>();
        for (Flight flight : flights) {
            AssignRunway(flight, solution);

        }
        for (Flight flight : solution.keySet()) {
            System.out.println(flight.getAirCraft().toString() + " " + solution.get(flight).toString() + "\n");
        }

    }
}
