import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<AirCraft> airCrafts = new ArrayList<>(createAirCrafts());
        airCrafts.sort(Comparator.<AirCraft, String>comparing(AirCraft::getName));
        viewCargoCapable(airCrafts);
        List<Flight> flightList = createFlights(airCrafts);
        List<Runway> runwayList = createRunways(6);

        Airport otopeni = new Airport("Otopeni", runwayList, airCrafts);
        Problem problem = new Problem(otopeni, flightList);

        problem.HomeworkSolution();
   /*     Bonus bonus = new Bonus(otopeni, flightList);
        bonus.schedueleFlights();*/
    }

    private static List<AirCraft> createAirCrafts() {
        return List.of(
                new Airliner("Airbus1", "1223", "white", 120, 23),
                new Airliner("Airbus2", "1229", "white", 123, 23),
                new Airliner("Airbus3", "1230", "white", 124, 23),
                new Airliner("Airbus4", "1231", "white", 125, 23),
                new Freighter("Boeing1", "1231", "white", 125, 23),
                new Freighter("Boeing2", "1232", "white", 126, 23)
        );
    }

    private static List<Flight> createFlights(List<AirCraft> airCrafts) {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(LocalTime.parse("08:03:00"), LocalTime.parse("08:40:00"), 1, airCrafts.get(0)));
        flights.add(new Flight(LocalTime.parse("09:00:01"), LocalTime.parse("10:00:00"), 2, airCrafts.get(1)));
        flights.add(new Flight(LocalTime.parse("09:30:00"), LocalTime.parse("11:00:00"), 3, airCrafts.get(2)));
        flights.add(new Flight(LocalTime.parse("10:30:00"), LocalTime.parse("12:00:00"), 4, airCrafts.get(3)));
        flights.add(new Flight(LocalTime.parse("11:30:00"), LocalTime.parse("12:00:00"), 5, airCrafts.get(4)));
        flights.add(new Flight(LocalTime.parse("12:30:00"), LocalTime.parse("13:00:00"), 6, airCrafts.get(5)));
        return flights;
    }


    private static List<Runway> createRunways(int count) {
        List<Runway> runways = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            runways.add(new Runway(String.valueOf(i), null));
        }
        return runways;
    }
    private static void viewCargoCapable(List<AirCraft> airCrafts) {

        System.out.println("View cargo capable:\n");
        for(AirCraft airCraft : airCrafts) {
            if(airCraft instanceof CargoCapable) {
                System.out.println(airCraft.toString()+"\n");
            }
        }

    }
}
