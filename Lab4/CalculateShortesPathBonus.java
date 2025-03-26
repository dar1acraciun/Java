package org.example;

import lombok.Getter;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.shortestpath.AllPairsShortestPath;
import org.graph4j.shortestpath.FloydWarshallShortestPath;
import org.graph4j.shortestpath.WarshallOverride;
import org.graph4j.util.Path;

import java.util.*;

@Getter

public class CalculateShortesPathBonus {

    private int numberOfLocations;
    private List<Location> locationList;
    private Graph graph;
    private Map<Route, Map<Type,Integer>> colectionOfType;

    public CalculateShortesPathBonus(int numberOfLocations, List<Location> locationList) {
        this.numberOfLocations = numberOfLocations;
        this.locationList = locationList;
        graph = GraphBuilder.empty()
                .estimatedNumVertices(numberOfLocations)
                .buildGraph();
        colectionOfType = new HashMap<>();
    }

    public void createGraph() {

        for (int i = 0; i < numberOfLocations; i++) {
            graph.addLabeledVertex(i, locationList.get(i));
        }

        for (var location : locationList) {
            int v = graph.findVertex(location);


            var neighborMap = location.getNeighbors();

            for (var neighbor : neighborMap.keySet()) {
                int u = graph.findVertex(neighbor);

                if (v < u) {
                    double length = neighborMap.get(neighbor);
                    double prob = calculateProbability(location.getType(), neighbor.getType(), length);
                    graph.addEdge(v, u, prob);
                }

            }
        }
        assert graph.numVertices() == numberOfLocations;
    }

    public double calculateProbability(Type type1, Type type2, double length) {

        double lenghtProb = 0;
        double safeProb = 0;

        if (length < 20) {
            lenghtProb = 0.7;
        } else if (length < 40) {
            lenghtProb = 0.5;
        } else lenghtProb = 0.4;

        if (type1 == Type.ENEMY && type2 == Type.ENEMY)
            safeProb = 0.1;
        else if (type1 == Type.ENEMY || type2 == Type.ENEMY)
            safeProb = 0.4;
        else safeProb = 1;

        return lenghtProb * safeProb;
    }

    public void calculateShortesPathBonus() {
        createGraph();
       WarshallOverride warshall = new WarshallOverride(graph);
      //  warshall.printMat();

        for (int i = 0; i < numberOfLocations; i++) {
            for (int j = 0; j < numberOfLocations; j++) {

                double dist = warshall.getPathWeight(i, j);
                Path path = warshall.findPath(i, j);
                Route route = new Route(locationList.get(i), locationList.get(j), dist);
                Map<Type, Integer> colectionAux = new HashMap<>();

                // Inițializare contor de tipuri
                colectionAux.put(Type.ENEMY, 0);
                colectionAux.put(Type.FRIENDLY, 0);
                colectionAux.put(Type.NEUTRAL, 0);


                Type startType = locationList.get(i).getType();
                Type endType = locationList.get(j).getType();;

                if (path != null) {
                    for (Integer node : path) {
                        Type type = locationList.get(node).getType();
                        colectionAux.put(type, colectionAux.get(type) + 1);
                    }
                }

                colectionOfType.put(route, colectionAux);
            }
        }
   //     printColectionOfType();

    }


    public void printColectionOfType() {
        for (var entry : colectionOfType.entrySet()) {
            System.out.println(entry.getKey().toString() + " -> " + entry.getValue());

        }
    }

    public void statistic()
    {
        Optional<Route> routeWithMostSafeZones = colectionOfType.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().getOrDefault(Type.FRIENDLY, 0)))
                .map(Map.Entry::getKey);

        Optional<Route> routeWithMostEnemyZones = colectionOfType.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().getOrDefault(Type.ENEMY, 0)))
                .map(Map.Entry::getKey);

        Optional<Route> routeWithMostNeutralZones = colectionOfType.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().getOrDefault(Type.NEUTRAL, 0)))
                .map(Map.Entry::getKey);
        System.out.println("Cu cele mai multe zone friendly"+routeWithMostSafeZones.get().toString());
        System.out.println("Cu cele mai multe zone enemy"+routeWithMostEnemyZones.get().toString());
        System.out.println("Cu cele mai multe zone neutral"+routeWithMostNeutralZones.get().toString());

    }




}

