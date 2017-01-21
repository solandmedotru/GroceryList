package ru.solandme.grocerylist.main.interactors;

public interface MainInteractorListener {
    void onGroceriesReceived();

    void onReceivedError(String error);

    void onComplite(String message);
}
