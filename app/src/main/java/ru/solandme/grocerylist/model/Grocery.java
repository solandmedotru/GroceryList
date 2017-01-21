package ru.solandme.grocerylist.model;

public class Grocery {
    private String name;

    public Grocery() {
    }

    public Grocery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
