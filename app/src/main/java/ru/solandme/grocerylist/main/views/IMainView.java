package ru.solandme.grocerylist.main.views;

import ru.solandme.grocerylist.model.Grocery;

public interface IMainView {
    void showMessage(String message);

    void updateGroceries();

    void addItem(Grocery item);
}
