package ru.solandme.grocerylist.main.interactors;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainInteractor {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("grocer");
    MainInteractorListener listener;

    public MainInteractor(MainInteractorListener listener) {
        this.listener = listener;
    }

    public void receiveRequest() {

    }
}


