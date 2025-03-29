package shell;

import Exception.ImageNotFound;
import Repository.CollectionOfImages;
import commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shell {
    private String commandLine;
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private CollectionOfImages collectionOfImages = new CollectionOfImages(new ArrayList<>());

    public Shell(CollectionOfImages collectionOfImages) {
        this.collectionOfImages = collectionOfImages;
    }

    public void terminal() throws ImageNotFound, IOException {
        System.out.println("Enter command: ");
        while (true) {
            System.out.print("\n> ");
            String commandLine = console.readLine();

            if (commandLine.equals(""))
                continue;

            switch (commandLine) {
                case "add":
                    handleAddCommand();
                    break;
                case "remove":
                    handleRemoveCommand();
                    break;
                case "update":
                    handleUpdateCommand();
                    break;
                case "load":
                    handleUploadCommand();
                    break;
                case "save":
                    handleSaveCommand();
                    break;
                case "report":
                    handleReportCommnad();
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
    private void handleReportCommnad() throws ImageNotFound, IOException {

        ReportCommand command=new ReportCommand("report",null,collectionOfImages);
        command.execute();
    }

    private void handleAddCommand() throws ImageNotFound, IOException {

        String nameOfFoto = promptForInput("Enter the name of the photo: ");
        String dateOfFoto = promptForInput("Enter the date of the photo: ");
        String pathOfFoto = promptForInput("Enter the path of the photo: ");
        String tagsOfFoto = promptForInput("Enter the tag of the photo: ");

        AddCommand command = new AddCommand("add", List.of(nameOfFoto, dateOfFoto, pathOfFoto, tagsOfFoto), collectionOfImages);
        command.execute();

    }

    private void handleRemoveCommand() throws ImageNotFound, IOException {

            String nameOfFoto = promptForInput("Enter the name of the photo to remove: ");
            RemoveCommand command = new RemoveCommand("remove", List.of(nameOfFoto), collectionOfImages);
            command.execute();

    }

    private void handleUpdateCommand() throws ImageNotFound, IOException {

        String nameOfFoto = promptForInput("Enter the name of the photo to update: ");
        String attribute = promptForInput("Enter the attribute of the photo: ");
        String updateValue = promptForInput("Enter the update value of the photo: ");

        UpdateCommand command = new UpdateCommand("update", List.of(nameOfFoto, attribute, updateValue), collectionOfImages);
        command.execute();

    }

    private void handleUploadCommand() throws ImageNotFound, IOException {
        String nameOfFile = promptForInput("Enter the path of the file to upload: ");
        UploadCommand command = new UploadCommand("update", List.of(nameOfFile), collectionOfImages);
        command.execute();

    }

    private void handleSaveCommand() throws ImageNotFound, IOException {
        String nameOfFile = promptForInput("Enter the name of the file to save: ");
        SaveCommand command = new SaveCommand("update", List.of(nameOfFile), collectionOfImages);
        command.execute();
    }
    private String promptForInput(String message) {

        try {
            System.out.print(message);
            return console.readLine();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

}

