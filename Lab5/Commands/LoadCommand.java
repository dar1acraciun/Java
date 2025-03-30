package Commands;

import Exceptions.InvalidArguments;
import Model.Image;
import Repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class LoadCommand extends Command {

    private String path;
    public LoadCommand(Repository repository, String path) {
        super(repository);
        this.path = path;
    }

    @Override
    public void execute() throws IOException {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            throw new InvalidArguments("Fișierul nu există sau este gol.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Repository repositoryLoad = objectMapper.readValue(file, Repository.class);


            repository.setImages(repositoryLoad.getImages());

            System.out.println("Load successful");
        } catch (JsonProcessingException e) {
            throw new InvalidArguments("Eroare la parsarea JSON: " + e.getMessage());
        } catch (IOException e) {
            throw new InvalidArguments("Nu se poate încărca JSON: " + e.getMessage());
        }
    }
}
