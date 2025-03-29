package commands;

import Exception.ImageNotFound;
import Exception.InvalidAtribute;
import Model.Image;
import Repository.CollectionOfImages;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportCommand extends Command {
    public ReportCommand(String command, List<String> arguments, CollectionOfImages collectionOfImages) {
        super(command, arguments, collectionOfImages);
    }

    @Override
    public void execute() throws InvalidAtribute, ImageNotFound, IOException {

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("file.resource.loader.path", "C:\\Users\\Asus-pc\\OneDrive\\Desktop\\templates");
        velocityEngine.init();

        VelocityContext context = new VelocityContext();

        List<Map<String, Object>> images = new ArrayList<>();

        for (Image image : collectionOfImages.getImages()) {
            Map<String, Object> image2 = new HashMap<>();
            image2.put("name", image.name());
            image2.put("date", image.date().toString());
            image2.put("tags", image.tags());
            image2.put("location", image.path());
            images.add(image2);

        }

        context.put("images", images);

        Template template = velocityEngine.getTemplate("report.vm");


        StringWriter writer = new StringWriter();

        template.merge(context, writer);


        try (FileWriter fileWriter = new FileWriter("report.html")) {
            fileWriter.write(writer.toString());
            System.out.println("Raportul HTML a fost generat cu succes!");
        } catch (Exception e) {
            throw new InvalidAtribute(e.getMessage());

        }

        File htmlFile = new File("report.html");
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
    }
}
