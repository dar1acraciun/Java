package Commands;

import Exceptions.InvalidArguments;
import Model.Image;
import Repository.Repository;

import java.io.*;


public class SaveLikeCommand extends Command {

    private String type;

    public SaveLikeCommand(Repository Repository, String type) {
        super(Repository);
        this.type = type;
    }

    @Override
    public void execute() throws Exception {
        switch (type) {
            case "binary":
                handleBinarySave();
                break;
            case "json":
                handleJsonSave();
                break;
            case "text":
                handleTextSave();
                break;
            default:
                throw new InvalidArguments("Invalid SaveLikeCommand");
        }

    }

    private void handleBinarySave() throws IOException {
        try {

            File myObj = new File("repository.bin");
            myObj.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(myObj));
            for (Image image : repository.getImages()) {
                out.writeObject(image);
            }
            System.out.println("Saved successfully");
            out.close();


        } catch (IOException e) {
            throw new InvalidArguments(e.getMessage());

        }
    }

    private void handleJsonSave() throws IOException, Exception {

        try {
            File myObj = new File("repository.json");
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            Command saveCommand = new SaveCommand(repository, myObj.getAbsolutePath());
            saveCommand.execute();


        } catch (IOException e) {
            throw new InvalidArguments(e.getMessage());
        }

    }

    private void handleTextSave() throws IOException, Exception {
        try {
            File myObj = new File("repository.txt");
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            FileWriter fw = new FileWriter(myObj);
            for (Image image : repository.getImages()) {
                fw.write(image.toString());
            }
            System.out.println("Saved successfully");
            fw.close();
        } catch (IOException e) {
            throw new InvalidArguments(e.getMessage());
        }
    }
}
