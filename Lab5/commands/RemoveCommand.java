package commands;

import Exception.ImageNotFound;
import Model.Image;
import Repository.CollectionOfImages;

import java.util.List;
import java.util.Optional;

public class RemoveCommand extends Command {

    public RemoveCommand(String command, List<String> arguments, CollectionOfImages collectionOfImages) {
        super(command, arguments, collectionOfImages);
    }

    @Override
    public void execute() throws ImageNotFound {
        String foto = arguments.get(0);
        Optional<Image> remove = collectionOfImages.getImages().stream().filter(s -> s.name().equals(foto)).findFirst();
        if (remove.isPresent()) {
            collectionOfImages.getImages().remove(remove.get());
            System.out.println("Removed " + remove.get().name());
        } else {
            throw new ImageNotFound("Error: Image '" + foto + "' not found in collection.");
        }
    }

}
