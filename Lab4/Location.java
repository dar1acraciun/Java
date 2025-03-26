package org.example;

import lombok.*;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Location implements Comparable<Location> {
    private String name;
    private Type type;
    private Map<Location,Integer> neighbors;

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'';

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Location o) {
        return 0;
    }


}
