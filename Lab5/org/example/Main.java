package org.example;

import Exception.ImageNotFound;
import Model.Image;
import Repository.CollectionOfImages;
import shell.Shell;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ImageNotFound, IOException {

        compulsory();
        homework();
    }

    static void compulsory() throws ImageNotFound, IOException {
        List<Image> images = new ArrayList<>();
        images.add(new Image("Sunset", LocalDate.of(2024, 3, 25), List.of("Java", "Horror", "CrimeScene"), "C:\\Users\\Asus-pc\\OneDrive\\Pictures\\Java.jpg"));
        CollectionOfImages collectionOfImages = new CollectionOfImages(images);
        //collectionOfImages.r("Sunset");
    }

    static void homework() throws ImageNotFound, IOException {
        List<Image> images = new ArrayList<>();
        images.add(new Image("Sunset", LocalDate.of(2024, 3, 25), List.of("Java", "Horror", "CrimeScene"), "r"));
        CollectionOfImages collectionOfImages = new CollectionOfImages(images);
        Shell shell = new Shell(collectionOfImages);
        shell.terminal();

    }

}