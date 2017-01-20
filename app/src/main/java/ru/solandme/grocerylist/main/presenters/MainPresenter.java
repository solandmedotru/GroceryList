package ru.solandme.grocerylist.main.presenters;

import ru.solandme.grocerylist.main.interactors.MainInteractor;
import ru.solandme.grocerylist.main.interactors.MainInteractorListener;
import ru.solandme.grocerylist.main.views.IMainView;

public class MainPresenter implements IMainPresenter, MainInteractorListener {

    private final IMainView mainView;
    private final MainInteractor interactor;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        this.interactor = new MainInteractor(this);
    }

    @Override
    public void receiveRequest() {
        interactor.receiveRequest();
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onGroceriesReceived() {

    }
}
