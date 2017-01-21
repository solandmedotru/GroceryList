package ru.solandme.grocerylist.main.presenters;

import ru.solandme.grocerylist.model.Grocery;

public interface IMainPresenter {
    void onAddItem(Grocery item);
}
