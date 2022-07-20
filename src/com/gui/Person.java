package com.gui;

public class Person {

    private String name;
    private Size size;
    private int height;
    private int weight;

    public Person(String name, Size size, int height, int weight) {
        this.name = name;
        this.size = size;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (h=" + height + ", w=" + weight + ", size=" + size+")";
    }

}
