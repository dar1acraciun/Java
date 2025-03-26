package org.example;

import java.util.List;
import java.util.Map;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Harta {

    private String name;
    private List<Location> locations;
    private List<Connection> connection;


}

