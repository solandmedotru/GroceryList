package ru.solandme.grocerylist.main.presenters;

import ru.solandme.grocerylist.model.Grocery;

public interface IMainPresenter {
    void receiveRequest();
    void onError(String message);

    void onAddItem(Grocery item);
}
