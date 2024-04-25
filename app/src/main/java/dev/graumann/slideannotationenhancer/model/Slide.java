package dev.graumann.slideannotationenhancer.model;

public class Slide {

    private String name;
    private String path;

    public Slide(String name, String path) {

        this.name = name;
        this.path = path;

    }

    public String getName() {
        return name;
    }

    public String getPath(){
        return path;
    }

    @Override
    public String toString() {
        return name;
    }
}
