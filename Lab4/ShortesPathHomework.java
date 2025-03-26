package org.example;

import lombok.Getter;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.shortestpath.DijkstraShortestPathHeap;

import java.util.List;
import java.util.Map;

@Getter

public class ShortesPathHomework {
    private int numberOfLocations;
    private List<Location> randomLoc;
    private Graph graph;
    private Map<Type, List<Location>> filterLoc;

    public ShortesPathHomework(int numberOfLocations, List<Location> randomLoc, Map<Type, List<Location>> filterLoc) {
        this.numberOfLocations = numberOfLocations;
        this.randomLoc = randomLoc;
        this.graph = GraphBuilder.empty()
                .estimatedNumVertices(numberOfLocations)
                .buildGraph();
        this.filterLoc = filterLoc;
    }

    public void createGraph() {

        for (int i = 0; i < numberOfLocations; i++) {
            graph.addLabeledVertex(i, randomLoc.get(i));
        }

        //create the edges of the graph
        for (var location : randomLoc) {
            //find the vertex number of the location object
            int v = graph.findVertex(location);
            var neighborMap = location.getNeighbors();
            for (var neighbor : neighborMap.keySet()) {
                int u = graph.findVertex(neighbor);
                //the test prevents adding the same edge twice
                if (v < u) {
                    //get the length of the road between the two locations
                    double length = neighborMap.get(neighbor);
                    //add a weighted edge in the graph
                    graph.addEdge(v, u, length);
                }
            }
        }
        assert graph.numVertices() == numberOfLocations;
    }

    public void algDiskstra() {
        createGraph();
        DijkstraShortestPathHeap dijkstra = new DijkstraShortestPathHeap(graph, 0);

        for (int i = 0; i < numberOfLocations; i++) {
            double dist = dijkstra.getPathWeight(i);
            System.out.println("Distance from START to " + randomLoc.get(i).getName() + " is: " + dist);
        }

    }

    public void calculateFilterMap() {

        DijkstraShortestPathHeap dijkstra = new DijkstraShortestPathHeap(graph, 0);
        Location minFrendly=new Location(), minEnemy=new Location(), minNeutral=new Location();
        double distFrendly = 9999, distEnemy = 9999, distNeutral = 9999;
        for (int i = 1; i < numberOfLocations; i++) {
            double dist = dijkstra.getPathWeight(i);

            if (distFrendly > dist && randomLoc.get(i).getType() == Type.FRIENDLY) {
                distFrendly = dist;
                minFrendly = randomLoc.get(i);
            }

            if (distEnemy > dist && randomLoc.get(i).getType() == Type.ENEMY) {
                distEnemy = dist;
                minEnemy = randomLoc.get(i);
            }

            if (distNeutral > dist && randomLoc.get(i).getType() == Type.NEUTRAL) {
                distNeutral = dist;
                minNeutral = randomLoc.get(i);
            }

        }
        System.out.println("Min distance from START to frendly: " +minFrendly.getName() + " is: " + distFrendly);
        System.out.println("Min distance from START to enemy: " + minEnemy.getName() + " is: " + distEnemy);
        System.out.println("Min distance from START to neutral: " + minNeutral.getName() + " is: " + distNeutral);


    }

}
