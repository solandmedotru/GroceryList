package ru.solandme.grocerylist.main.presenters;

import ru.solandme.grocerylist.main.interactors.MainInteractor;
import ru.solandme.grocerylist.main.interactors.MainInteractorListener;
import ru.solandme.grocerylist.main.views.IMainView;
import ru.solandme.grocerylist.model.Grocery;

public class MainPresenter implements IMainPresenter, MainInteractorListener {

    private final IMainView mainView;
    private final MainInteractor interactor;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        this.interactor = new MainInteractor(this);
    }

    @Override
    public void onMessage(String message) {
        mainView.showMessage(message);
    }

    @Override
    public void onAddItem(Grocery item, String listId) {
        interactor.addItemToList(item, listId);
    }
}
