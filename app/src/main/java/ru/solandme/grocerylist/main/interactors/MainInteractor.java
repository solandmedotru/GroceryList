package ru.solandme.grocerylist.main.interactors;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.solandme.grocerylist.model.Grocery;

public class MainInteractor {

    private static final String TAG = MainInteractor.class.getSimpleName();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("shoppingLists");
    MainInteractorListener listener;
    String key = "home";

    public MainInteractor(MainInteractorListener listener) {
        this.listener = listener;
        rootRef.child(key).keepSynced(true);
    }


    public void addItemToList(Grocery item, String key) {

        rootRef.child(key).push().setValue(item, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    listener.onMessage(databaseError.getMessage());
                } else {
                    listener.onMessage("Date saved successfully.");
                }
            }
        });
    }
}


