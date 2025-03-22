package org.example;

import com.github.javafaker.Faker;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.shortestpath.DijkstraShortestPathBase;
import org.graph4j.*;

import java.util.*;
import java.util.stream.Collectors;

//import java.awt.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Location> locationList = new ArrayList<>();
        createLocations(locationList);

        Set<Location> friendlyLoc = new TreeSet<>();
        friendlyLoc = locationList.stream().filter(loc -> loc.getType() == Type.FRENDLY).collect(Collectors.toSet());

        System.out.println("friendly list");
        for (Location loc : friendlyLoc) {
            System.out.println(loc.getName());
        }

        List<Location> enemyLoc = new LinkedList<>();
        enemyLoc = locationList.stream().filter(loc -> loc.getType() == Type.ENEMY)
                .sorted(Comparator.comparing(Location::getName)).
                sorted(Comparator.comparing(Location::getType)).toList();

        System.out.println("EnemyList:");
        for (Location loc : enemyLoc)
            System.out.println(loc.getName());
        List<Location> randomLoc=new ArrayList<>();
        createRandomLocations(5,randomLoc);
        for(Location loc:randomLoc){
            System.out.println(loc.getName()+' '+loc.getType());

        }
   /*   Graph g = GraphBuilder.numVertices(5).named("K3")
                .addEdge(0,1).addEdge(1,2).addEdge(0,2).addEdge(1,3).addEdge(2,3)
                .buildGraph();
        g.addEdge(0,1,2);
        g.addEdge(1,2,3);
        g.addEdge(0,2,4);
        g.addEdge(1,3,4);
        DijkstraShortestPathBase dijkstra= new DijkstraShortestPathBase( g,0);
        System.out.println("Drumul: " + dijkstra.getPath(3));*/


    }

    static void createLocations(List<Location> locationList) {

        Location start = new Location("Start", Type.FRENDLY);
        locationList.add(start);
        locationList.add(new Location("1", Type.ENEMY));
        locationList.add(new Location("2", Type.NEUTRAL));
        locationList.add(new Location("3", Type.FRENDLY));
        locationList.add(new Location("4", Type.NEUTRAL));
        locationList.add(new Location("5", Type.FRENDLY));
        locationList.add(new Location("6", Type.ENEMY));


    }
    static void createRandomLocations(int count, List<Location> randomLoc) {

        Faker faker = new Faker();
        Random random = new Random();
    randomLoc.add(new Location("START",Type.FRENDLY));

        for(int i = 1; i < count; i++) {

            Type randomType = Type.values()[random.nextInt(Type.values().length)];
            randomLoc.add(new Location(faker.address().cityName(),randomType));
        }
    }
}