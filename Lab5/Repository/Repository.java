package Repository;

import Exceptions.ImageNotFound;
import Model.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Getter
@Setter


public class Repository {
    public List<Image> images;

    public Repository(@JsonProperty("images") List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {

        try {

            Path p1 = Paths.get(image.path());
            if (Files.exists(p1)) {
                images.add(image);
            } else {
                System.out.println("Image not found");
            }

        } catch (InvalidPathException e) {
            throw new ImageNotFound("Invald path");

        }
    }

    public Image findImageByName(String name) throws ImageNotFound {

        return images.stream().filter(img -> img.name().equals(name)).
                findFirst().orElseThrow(() -> new ImageNotFound("Image " + name + " not found"));

    }

    public void printImage(String name) throws ImageNotFound {
        Image image = findImageByName(name);
        File imgFile = new File(image.path());
        if (Desktop.isDesktopSupported() && imgFile.exists()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(imgFile);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            System.out.println("Desktop API nu este suportat sau fișierul nu există.");
        }
    }

}
