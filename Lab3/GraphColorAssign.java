import java.util.*;

public class GraphColorAssign {
    private final Airport airport;
    private final List<Runway> runways;
    private final List<Flight> flights;
    private final int matOfFlight[][];
    private final int colors[];
    private int minimNumberRunways;

    public GraphColorAssign(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
        this.runways = airport.getRunways();
        matOfFlight = new int[flights.size()][flights.size()];
        colors = new int[flights.size()];
    }

    public void bonus() {
        makeGraph();
        minimNumberRunways = graphColoring();
        if (minimNumberRunways <= runways.size()) {
            Map<Integer, List<Flight>> setsUnstable = new HashMap<Integer, List<Flight>>();
            createSets(setsUnstable,runways.size());
            stableTheSets(setsUnstable);
            printFinalSolution(setsUnstable);
        } else {
            System.out.println("nr minim necesar de piste: " + graphColoring());
            delayingFlights();
        }
    }

    public void makeGraph() {

        for (int i = 0; i < flights.size(); i++) {
            for (int j = i + 1; j < flights.size(); j++) {
                Flight flight1 = flights.get(i), flight2 = flights.get(j);
                if (flight1.getStartTimeArrival().isBefore(flight2.getEndTimeArrival()) && flight1.getEndTimeArrival().isAfter(flight2.getStartTimeArrival())) {
                    matOfFlight[i][j] = matOfFlight[j][i] = 1;
                } else
                    matOfFlight[i][j] = matOfFlight[j][i] = 0;


            }

        }

    }

    public int graphColoring() {
        int numFlights = flights.size();

        Arrays.fill(colors, -1);
        colors[0] = 0;

        for (int i = 1; i < numFlights; i++) {
            boolean[] availableColors = new boolean[numFlights];
            Arrays.fill(availableColors, true);

            for (int j = 0; j < numFlights; j++) {
                if (matOfFlight[i][j] == 1 && colors[j] != -1) {
                    availableColors[colors[j]] = false;
                }
            }

            int color;
            for (color = 0; color < numFlights; color++) {
                if (availableColors[color]) {
                    break;
                }
            }

            colors[i] = color;
        }

        int maxColor = -1;
        for (int i = 0; i < numFlights; i++) {
            colors[i] = colors[i] + 1;
            if (colors[i] > maxColor) {
                maxColor = colors[i];
            }
        }

        return maxColor;
    }

    public void createSets(Map<Integer, List<Flight>> setsUnstable,int numberOfSets) {


        for (int i = 1; i <= numberOfSets; i++) {
            List<Flight> flightsUnstable = new ArrayList<Flight>();
            for (int j = 0; j < flights.size(); j++) {
                if (colors[j] == i)
                    flightsUnstable.add(flights.get(j));
            }
            setsUnstable.put(i, flightsUnstable);
        }


    }

    public void stableTheSets(Map<Integer, List<Flight>> setsUnstable) {

        for (Map.Entry<Integer, List<Flight>> entry1 : setsUnstable.entrySet()) {
            for (Map.Entry<Integer, List<Flight>> entry2 : setsUnstable.entrySet()) {
                int size1 = entry1.getValue().size();
                int size2 = entry2.getValue().size();
                int diff = Math.abs(size1 - size2);
                List<Flight> max;

                while (Math.abs(size1 - size2) > 1) {
                    if (size1 > size2) {

                        Flight flightToMove = entry1.getValue().removeLast();
                        entry2.getValue().add(flightToMove);
                        size1--;
                        size2++;
                    } else {
                        Flight flightToMove = entry2.getValue().removeLast();
                        entry1.getValue().add(flightToMove);
                        size1++;
                        size2--;
                    }
                }
            }
        }

    }

    public void printFinalSolution(Map<Integer, List<Flight>> setsUnstable) {
        for (Map.Entry<Integer, List<Flight>> entry : setsUnstable.entrySet()) {
            System.out.println("Runway " + entry.getKey() + ":");
            for (Flight flight : entry.getValue()) {
                System.out.println("  Flight: " + flight);
            }
        }
    }

    public List<Flight> findMinSet(int minimNumberRunways) {
        Map<Integer, List<Flight>> setsUnstable=new HashMap<>();
        List<Flight> minSet = null;
        int minSetSize = Integer.MAX_VALUE;
        createSets(setsUnstable,minimNumberRunways);
        for (Map.Entry<Integer, List<Flight>> entry : setsUnstable.entrySet()) {
            if(entry.getValue().size()<minSetSize){
                minSet = entry.getValue();
                minSetSize = entry.getValue().size();
            }
        }
        return minSet;
    }

    public void delayingFlights() {
        while (minimNumberRunways > runways.size()) {

            List<Flight> minSet = findMinSet(minimNumberRunways);
            for(Flight flight : minSet ) {
                System.out.println("Amânăm zborul: " + flight.toString());
                flights.remove(flight);
               flight.setStartTimeArrival( flight.getStartTimeArrival().plusMinutes(30));
                flight.setEndTimeArrival(flight.getEndTimeArrival().plusMinutes(30));
                flights.add(flight);
            }

            bonus();

        }
    }



}
