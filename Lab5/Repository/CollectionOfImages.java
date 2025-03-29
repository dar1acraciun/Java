package Repository;


import Exception.ImageNotFound;
import Model.Image;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CollectionOfImages {
    private List<Image> images;

   /* public CollectionOfImages(List<Image> images) {
        this.images = images;
    }*/

    public CollectionOfImages() {
        this.images = new ArrayList<>();
    }

    @JsonCreator
    public CollectionOfImages(@JsonProperty("images") List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Image findImageByName(String name) throws ImageNotFound {

        return images.stream().filter(img -> img.name().equals(name)).
                findFirst().orElseThrow(() -> new ImageNotFound("Image " + name + " not found"));

    }

    public void addImage(Image image) throws ImageNotFound,IOException {

        File imgFile = new File(image.path());
        System.out.println(image.path());
        try  {
            Files.newInputStream(Paths.get(image.path())).close(); //verf daca exista
            images.add(image);

        } catch (IOException e){
           throw new ImageNotFound("Image " + image.name() + " not found");
        }
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
