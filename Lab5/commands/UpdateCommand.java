package commands;

import Exception.*;
import Model.Image;
import Repository.CollectionOfImages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateCommand extends Command {
    public UpdateCommand(String command, List<String> arguments, CollectionOfImages collectionOfImages) {
        super(command, arguments, collectionOfImages);
    }

    @Override
    public void execute() throws ImageNotFound {

        Image newImage=new Image(null,null,null,null);

        Optional<Image> image = collectionOfImages.getImages().stream().filter(s -> s.name().equals(arguments.get(0))).findFirst();
        if (!image.isPresent()) {
            throw new ImageNotFound("Nu s a gasit imaginea " + arguments.get(0));
        }
        else {
            switch (arguments.get(1)) {
                case "date":
                    updateDate(image.get(),newImage);
                    break;
                case "name":
                    updateName(image.get(),newImage);
                    break;
                case "tags":
                    updateTags(image.get(),newImage);
                    break;
                case "path":
                    updatePath(image.get(),newImage);
                    break;
                default:
                    throw new InvalidAtribute("Nu s a gasit atributul " + arguments.get(1));

            }
        }


    }
    public void updateDate(Image image,Image newImage) throws ImageNotFound,NoSuchElementException {
        try{

            newImage=new Image(image.name(),LocalDate.parse(arguments.get(2), DateTimeFormatter.ofPattern("dd/MM/yyyy")),image.tags(),image.path());
            collectionOfImages.getImages().add(newImage);
            collectionOfImages.getImages().remove(image);
            System.out.println("Succesfully updated");
        }
        catch (DateTimeParseException e)
        {
            throw new InvalidAtribute("Invalid date format! Use dd.MM.yyyy");
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new InvalidAtribute("Completeaza campurile corespunzator!!");

        }
    }
    public void updateName(Image image,Image newImage) throws ImageNotFound,NoSuchElementException {
        try{
            newImage=new Image(arguments.get(2),image.date(),image.tags(),image.path());
            collectionOfImages.getImages().add(newImage);
            collectionOfImages.getImages().remove(image);
            System.out.println("Succesfully updated");

        }catch (NullPointerException e)
        {
            throw new InvalidAtribute("Invalid name format!");
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new InvalidAtribute("Completeaza campurile corespunzator!!");
        }

    }
    public void updateTags(Image image,Image newImage) throws ImageNotFound,NoSuchElementException {

        try{
            newImage=new Image(image.name(),image.date(),List.of(arguments.get(2)),image.path());
            collectionOfImages.getImages().add(newImage);
            collectionOfImages.getImages().remove(image);
            System.out.println("Succesfully updated");

        }catch (NullPointerException e)
        {
            throw new InvalidAtribute("Invalid tags format!");
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new InvalidAtribute("Completeaza campurile corespunzator!!");
        }
    }
    public void updatePath(Image image,Image newImage) throws ImageNotFound,NoSuchElementException {
        try{
            newImage=new Image(image.name(),image.date(),image.tags(),arguments.get(2));
            collectionOfImages.getImages().add(newImage);
            collectionOfImages.getImages().remove(image);
            System.out.println("Succesfully updated");

        }catch (NullPointerException e)
        {
            throw new InvalidAtribute("Invalid path format!");
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new InvalidAtribute("Completeaza campurile corespunzator!!");
        }
    }

}
