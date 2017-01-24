package ru.solandme.grocerylist.main.presenters;

import ru.solandme.grocerylist.model.Grocery;

public interface IMainPresenter {
    void addItem(Grocery item, String listId);
}
