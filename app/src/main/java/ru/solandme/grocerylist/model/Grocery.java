package ru.solandme.grocerylist.model;

public class Grocery {
    private String name;
    private String listID;
    private String owner;

    public Grocery() {
    }

    public Grocery(String name, String listID) {
        this.name = name;
        this.listID = listID;
        this.owner = "Anonymous";
    }

    public Grocery(String name, String listID, String owner) {
        this.name = name;
        this.listID = listID;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
