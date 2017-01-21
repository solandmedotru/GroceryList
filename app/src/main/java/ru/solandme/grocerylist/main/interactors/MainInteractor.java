package ru.solandme.grocerylist.main.interactors;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ru.solandme.grocerylist.model.Grocery;

public class MainInteractor {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("grocers");
    MainInteractorListener listener;
    List<Grocery> groceries;

    public MainInteractor(MainInteractorListener listener, List<Grocery> groceries) {
        this.listener = listener;
        this.groceries = groceries;
    }

    public void receiveRequest() {
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void addItemToList(Grocery item) {
        groceries.add(item);
        rootRef.setValue(groceries, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    listener.onReceivedError(databaseError.getMessage());
                } else {
                    listener.onComplite("Date saved successfully.");
                }
            }
        });
    }
}


