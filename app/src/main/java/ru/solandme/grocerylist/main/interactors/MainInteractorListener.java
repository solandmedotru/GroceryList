package ru.solandme.grocerylist.main.interactors;

public interface MainInteractorListener {
    void onGroceriesReceived();

    void onMessage(String error);
}
