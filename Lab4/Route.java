package org.example;

import lombok.Getter;
import lombok.Setter;


@Getter
public class Route {

   private Location node1;
   private Location node2;
   private double distance;

    @Override
    public String toString() {
        return "From " + node1.getName() +
                " to " + node2.getName() +
                "-" +
                "-> probability=" + distance+'%';
    }

    public Route(Location node1, Location node2, double distance) {
        this.node1 = node1;
        this.node2 = node2;
        this.distance = distance;
    }
}
