package Commands;

import Exceptions.InvalidArguments;
import Repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SaveCommand extends Command {
    private String path;
    public SaveCommand(Repository repository, String path) {
        super(repository);
        this.path = path;
    }

    @Override
    public void execute() throws IOException {
        try {
            File image = new File(path);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(image, repository);
            System.out.println("Repository saved successfully to: " + path);
        }catch (IOException e){
            throw new InvalidArguments(e.getMessage());
        }

    }
}
