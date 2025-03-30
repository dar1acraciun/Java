package Commands;

import Exceptions.ImageNotFound;
import Model.Image;
import Repository.Repository;

import java.util.Optional;

public class RemoveCommand extends Command {
    private String nameofimage;
    public RemoveCommand(Repository repository, String nameofimage) {
        super(repository);
        this.nameofimage=nameofimage;
    }

    @Override
    public void execute() {

        Optional<Image> remove = repository.getImages().stream().filter(s -> s.name().equals(nameofimage)).findFirst();
        if (remove.isPresent()) {
            repository.getImages().remove(remove.get());
            System.out.println("Removed " + remove.get().name());
        } else {
            throw new ImageNotFound("Error: Image '" + nameofimage + "' not found in collection.");
        }
    }
}
