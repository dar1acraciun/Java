package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

//import java.awt.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static List<Location> locationList = new ArrayList<>();
    private static List<Location> randomLoc = new ArrayList<>();
    private static int numberOfLocations;

    public static void main(String[] args) {

        compulsory();
        homework();
        bonus();

    }

    static void bonus() {

        System.out.println("----BONUS-------");
        CalculateShortesPathBonus bonus = new CalculateShortesPathBonus(numberOfLocations, randomLoc);
        bonus.calculateShortesPathBonus();
        bonus.statistic();

    }

    static void homework() {
        System.out.println("----Homework-------");

        numberOfLocations = 500;
        createRandomLocations(randomLoc);


        Map<Type, List<Location>> filterLoc = new HashMap<>();
        List<Location> friendly = randomLoc.stream().filter(loc -> loc.getType() == Type.FRIENDLY).toList();
        filterLoc.put(Type.FRIENDLY, friendly);

        List<Location> enemy = randomLoc.stream().filter(loc -> loc.getType() == Type.ENEMY).toList();
        filterLoc.put(Type.ENEMY, enemy);

        List<Location> neutral = randomLoc.stream().filter(loc -> loc.getType() == Type.NEUTRAL).toList();
        filterLoc.put(Type.NEUTRAL, neutral);

        ShortesPathHomework homework = new ShortesPathHomework(numberOfLocations, randomLoc, filterLoc);
        homework.algDiskstra();
        homework.calculateFilterMap();
    }

    static void compulsory() {

        System.out.println("---Compulsory---");
        createLocations(locationList);

        Set<Location> friendlyLoc = new TreeSet<>();
        friendlyLoc = locationList.stream().filter(loc -> loc.getType() == Type.FRIENDLY).collect(Collectors.toSet());

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
    }

    static void createLocations(List<Location> locationList) {

        Location start = new Location("Start", Type.FRIENDLY, null);
        locationList.add(start);
        locationList.add(new Location("1", Type.ENEMY, null));
        locationList.add(new Location("2", Type.NEUTRAL, null));
        locationList.add(new Location("3", Type.FRIENDLY, null));
        locationList.add(new Location("4", Type.NEUTRAL, null));
        locationList.add(new Location("5", Type.FRIENDLY, null));
        locationList.add(new Location("6", Type.ENEMY, null));


    }

    static void createRandomLocations(List<Location> randomLoc) {

        Faker faker = new Faker();
        Random random = new Random();
        randomLoc.add(new Location("START", Type.FRIENDLY, new HashMap<>()));

        for (int i = 1; i < numberOfLocations; i++) {

            Type randomType = Type.values()[random.nextInt(Type.values().length)];
            randomLoc.add(new Location(faker.address().cityName(), randomType, new HashMap<>()));
        }

        for (int i = 0; i < numberOfLocations - 1; i++) {
            var loc1 = randomLoc.get(i);
            for (int j = i + 1; j < numberOfLocations; j++) {
                var loc2 = randomLoc.get(j);
                if (random.nextBoolean()) {
                    int length = random.nextInt(50) + 1;
                    Map<Location, Integer> aux = loc1.getNeighbors();
                    aux.put(loc2, length);
                    loc1.setNeighbors(aux);
                    aux = loc2.getNeighbors();
                    aux.put(loc1, length);
                    loc2.setNeighbors(aux);
                }
            }

        }

    }

}