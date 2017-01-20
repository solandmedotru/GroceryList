package ru.solandme.grocerylist.main.presenters;

public interface IMainPresenter {
    void receiveRequest();

    void onError(String message);
}
