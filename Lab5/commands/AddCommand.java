package commands;

import Exception.ImageNotFound;
import Exception.InvalidAtribute;
import Model.Image;
import Repository.CollectionOfImages;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AddCommand extends Command {

    public AddCommand(String command, List<String> arguments, CollectionOfImages collectionOfImages) {
        super(command, arguments, collectionOfImages);
    }

    @Override
    public void execute() throws ImageNotFound, IOException {

        try {
            Image image = new Image(arguments.get(0), LocalDate.parse(arguments.get(1), DateTimeFormatter.ofPattern("dd/MM/yyyy")), List.of(arguments.get(3)), arguments.get(2));
            collectionOfImages.addImage(image);
            System.out.print("Added Image");
        } catch (DateTimeParseException e) {
            throw new InvalidAtribute("Invalid date format! Use dd.MM.yyyy");
        }

    }
}
