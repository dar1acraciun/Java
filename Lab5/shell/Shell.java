package Shell;

import Commands.*;
import Exceptions.CantReadFrom;
import Exceptions.InvalidArguments;
import Repository.Repository;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@Setter

public class Shell {
    public Repository repository;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Shell(Repository repository) {
        this.repository = repository;
    }

    public void terminal() throws IOException {


        while (true) {
            try {

                String command = br.readLine();
                switch (command) {
                    case "add":
                        handleAddError();
                        break;
                    case "remove":
                        handleRemoveCommand();
                        break;
                    case "update":
                        handleUpdateCommand();
                        break;
                    case "load":
                        handleLoadCommand();
                        break;
                    case "save":
                        handeSaveCommand();
                        break;
                    case "raport":
                        handleReportCommand();
                        break;
                    case "save like":
                        handleSaveLikeCommand();
                        break;
                    case "bonus":
                        handleBonusCommand();
                        break;
                    case "AddAll":
                        handleAddAllCommand();
                        break;
                    default:
                      System.out.println("Invalid command");
                      continue;
                }

            } catch (IOException e) {
                throw new CantReadFrom("Cant Read From terminal");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    private void handleAddError() throws Exception {


        System.out.println("Name of image:");
        String imageName = br.readLine();
        System.out.println("Path of image:");
        String path = br.readLine();
        System.out.println("Date of image:");
        String date = br.readLine();
        System.out.println("tags of image:");
        String tags = br.readLine();
        String[] tagsSep = tags.split(" ");
        Command command = new AddCommand(imageName, path, date, tagsSep, repository);
        command.execute();


    }

    private void handleRemoveCommand() throws Exception {

        System.out.println("Name of image:");
        String imageName = br.readLine();
        Command command = new RemoveCommand(repository, imageName);
        command.execute();


    }

    private void handleUpdateCommand() throws Exception {
        System.out.println("Name of image:");
        String imageName = br.readLine();
        Command command = new UpdateCommand(repository, imageName);
        command.execute();

    }

    private void handleSaveLikeCommand() throws Exception {
        System.out.println("choose binary,json,text:");
        String type = br.readLine();
        Command command = new SaveLikeCommand(repository, type);
        command.execute();

    }

    private void handleLoadCommand() throws Exception {
        System.out.println("Path of the file:");
        String path = br.readLine();
        Command command = new LoadCommand(repository, path);
        command.execute();

    }

    private void handeSaveCommand() throws Exception {
        System.out.println("Path of the file:");
        String path = br.readLine();
        Command command = new SaveCommand(repository, path);
        command.execute();
    }

    private void handleReportCommand() throws Exception {
        RaportCommand command = new RaportCommand(repository);
        command.execute();

    }

    private void handleAddAllCommand() throws Exception {
        System.out.println("Name of directory:");
        String directory = br.readLine();
        Command command = new AddAllCommand(repository, directory);
        command.execute();
    }

    private void handleBonusCommand() throws Exception {
        FindMaxCliq cliq = new FindMaxCliq(repository);
        cliq.execute();

    }

}
