package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Connection {

    private Location firstPoint, lastPoint;
    private boolean connected;
    private double probabilityOfSafety;
    private double time;


}
