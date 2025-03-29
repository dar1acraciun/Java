package commands;

import Exception.ImageNotFound;
import Exception.InvalidAtribute;
import Repository.CollectionOfImages;

import java.io.IOException;
import java.util.List;

public abstract class Command {

    protected String command;
    protected List<String> arguments;
    protected CollectionOfImages collectionOfImages;

    public Command(String command, List<String> arguments, CollectionOfImages collectionOfImages) {
        this.command = command;
        this.arguments = arguments;
        this.collectionOfImages = collectionOfImages;
    }

    public CollectionOfImages getCollectionOfImages() {
        return collectionOfImages;
    }

    public void setCollectionOfImages(CollectionOfImages collectionOfImages) {
        this.collectionOfImages = collectionOfImages;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    protected abstract void execute() throws InvalidAtribute, ImageNotFound, IOException;
}
