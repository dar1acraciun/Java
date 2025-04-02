package org.example;

import Model.Image;
import Repository.Repository;
import Shell.Shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Repository collection = new Repository(new ArrayList<Image>());

    public static void main(String[] args) throws IOException {

        compursory();
        homework();

    }

    static void compursory() {

        List<Image> images = new ArrayList<>();
        String[] tags = {"horror"};
        Image image = new Image("Java", "C:\\Users\\Asus-pc\\OneDrive\\Desktop\\Java\\Lab5vs2\\src\\main\\resources\\Java.jpg", "12/12/2003", tags);
        collection.addImage(image);
    }

    static void homework() throws IOException {

        Repository collection2 = new Repository(new ArrayList<Image>());
        String[] tags = {"horror"};
        Image image = new Image("Java", "C:\\Users\\Asus-pc\\OneDrive\\Desktop\\Java\\Lab5vs2\\src\\main\\resources\\Java.jpg", "12/12/2003", tags);
        collection2.addImage(image);
        Shell s = new Shell(collection2);
        s.terminal();

    }

}