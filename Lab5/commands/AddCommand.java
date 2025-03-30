package Commands;

import Exceptions.ImageNotFound;
import Model.Image;
import Repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

@Getter
@Setter

public class AddCommand extends Command {

    private String imageName;
    private String path;
    private String date;
    private String[] tags;


    public AddCommand(String imageName, String path, String date, String[] tags, Repository Repository) {
        super(Repository);
        this.imageName = imageName;
        this.path = path;
        this.date = date;
        this.tags = tags;

    }

    @Override
    public void execute() {

        try{
            Image image=new Image(imageName,path,date,tags);
            repository.addImage(image);
        }
        catch (ImageNotFound e)
        {
            System.out.println("Image not found");
        }
    }
}
