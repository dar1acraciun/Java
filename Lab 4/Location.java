package org.example;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Location implements Comparable<Location> {
    private String name;
    private Type type;


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Location o) {
        return 0;
    }


}
