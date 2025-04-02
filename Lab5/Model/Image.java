package Model;

import java.io.Serializable;
import java.util.List;

public record  Image(String name, String path, String date, String[] tags) implements Serializable {

}
