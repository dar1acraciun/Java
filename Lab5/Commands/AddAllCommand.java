package Commands;

import Exceptions.InvalidArguments;
import Model.Image;
import Repository.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class AddAllCommand extends Command {

    private String directory;
    private List<String> randTags = new ArrayList<>();

    public AddAllCommand(Repository collection, String directory) {
        super(collection);
        this.directory = directory;
        randTags = List.of("sun", "happy", "horror", "sad", "forest", "scary");
    }

    @Override
    public void execute() throws Exception {

        List<Image> images = new ArrayList<>();
        Path dir = Paths.get(directory);

        try (Stream<Path> stream = Files.walk(Paths.get(directory))) {

            List<Path> img = stream.filter(p -> p.toString().endsWith(".png") || p.toString().endsWith(".jpg")).toList();
            for (Path path : img) {
                Image image = new Image(path.getFileName().toString(), path.toAbsolutePath().toString(), Files.getLastModifiedTime(path).toString(),
                        generateTags());
                images.add(image);
            }

            images.addAll(repository.getImages());
            repository.setImages(images);
            System.out.println("Adding " + images.size() + " images");

        } catch (Exception e) {
            throw new InvalidArguments(e.getMessage());
        }

    }

    private String[] generateTags() {
        int count = (int) (Math.random() * randTags.size());
        Set<String> selectedTags = new HashSet<>();
        Random random = new Random();

        while (selectedTags.size() < count) {
            int index = random.nextInt(randTags.size());
            selectedTags.add(randTags.get(index));
        }

        return selectedTags.toArray(new String[0]);
    }
}
