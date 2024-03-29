package dev.graumann.slideannotationenhancer.model;

import java.io.File;
// import java.net.URISyntaxException;

public class Slide {

    private String name;
    private File file;

    public Slide(String name, String path) {

        this.name = name;
        /*
        try {
            file = new File(this.getClass().getResource(path).toExternalForm());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        */

        try {
            file = new File(this.getClass().getResource(path).toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return name;
    }
}
