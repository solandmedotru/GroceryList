package ru.solandme.grocerylist.main.presenters;

import ru.solandme.grocerylist.model.Grocery;

public interface IMainPresenter {
    void refreshGroceries();
    void onAddItem(Grocery item);
}
