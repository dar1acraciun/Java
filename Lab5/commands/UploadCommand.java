package commands;

import Repository.CollectionOfImages;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UploadCommand extends Command {
    public UploadCommand(String command, List<String> arguments, CollectionOfImages collectionOfImages) {
        super(command, arguments, collectionOfImages);
    }

    @Override
    public void execute() {


        String filePath = arguments.get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(java.time.LocalDate.class, new com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer(dateFormatter));

        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        try {
            CollectionOfImages loadedRepository = objectMapper.readValue(new File(filePath), CollectionOfImages.class);
            collectionOfImages.setImages(loadedRepository.getImages());
            System.out.println("Repository loaded successfully from: " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading repository from file: " + e.getMessage());
        }
    }
}
