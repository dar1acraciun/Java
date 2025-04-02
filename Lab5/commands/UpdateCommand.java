package Commands;

import Exceptions.InvalidArguments;
import Model.Image;
import Repository.Repository;

import java.util.Optional;

public class UpdateCommand extends Command {
    private final String imageName;
    private String atribute;
    private String newValue;
    private Image newImage;

    public UpdateCommand(Repository repository, String imageName) {
        super(repository);
        this.imageName = imageName;
    }

    @Override
    public void execute() {


        Optional<Image> image = repository.getImages().stream().filter(s -> s.name().equals(imageName)).findFirst();
        switch (atribute) {
            case "name":
                updateName(image.get());
                break;
            case "date":
                updateDate(image.get());
                break;
            case "path":
                updatePath(image.get());
                break;
            case "tags":
                updateTags(image.get());
                break;
            default:
                throw new InvalidArguments("Error: Invalid atribute '" + atribute + "'.");

        }

    }

    private void updateName(Image image) {
        try {
            newImage = new Image(newValue, image.path(), image.date(), image.tags());
            repository.getImages().add(newImage);
            repository.getImages().remove(image);
            System.out.println("Succesfully updated");

        } catch (NullPointerException e) {
            throw new InvalidArguments("Invalid name format!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArguments("Completeaza campurile corespunzator!!");
        }
    }

    private void updateDate(Image image) {
        try {
            newImage = new Image(imageName, image.path(), newValue, image.tags());
            repository.getImages().add(newImage);
            repository.getImages().remove(image);
            System.out.println("Succesfully updated");

        } catch (NullPointerException e) {
            throw new InvalidArguments("Invalid name format!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArguments("Completeaza campurile corespunzator!!");
        }
    }

    private void updateTags(Image image) {
        String[] tags = image.tags();
        tags[tags.length - 1] = newValue;
        try {
            newImage = new Image(imageName, image.path(), image.date(), tags);
            repository.getImages().add(newImage);
            repository.getImages().remove(image);
            System.out.println("Succesfully updated");

        } catch (NullPointerException e) {
            throw new InvalidArguments("Invalid name format!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArguments("Completeaza campurile corespunzator!!");
        }
    }

    private void updatePath(Image image) {

        try {
            newImage = new Image(imageName, newValue, image.date(), image.tags());
            repository.getImages().add(newImage);
            repository.getImages().remove(image);
            System.out.println("Succesfully updated");

        } catch (NullPointerException e) {
            throw new InvalidArguments("Invalid name format!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArguments("Completeaza campurile corespunzator!!");
        }
    }


}
