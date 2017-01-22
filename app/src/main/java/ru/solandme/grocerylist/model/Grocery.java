package ru.solandme.grocerylist.model;

public class Grocery {
    private String name;
    private String ListID;

    public Grocery() {
    }

    public Grocery(String name, String listID) {
        this.name = name;
        ListID = listID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListID() {
        return ListID;
    }

    public void setListID(String listID) {
        ListID = listID;
    }
}
