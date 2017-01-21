package ru.solandme.grocerylist.main.interactors;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ru.solandme.grocerylist.model.Grocery;

public class MainInteractor {

    private static final String TAG = MainInteractor.class.getSimpleName();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("grocers");
    MainInteractorListener listener;
    List<Grocery> groceries;

    public MainInteractor(MainInteractorListener listener, List<Grocery> groceries) {
        this.listener = listener;
        this.groceries = groceries;
    }

    public void refreshGroceries() {
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Grocery>> t = new GenericTypeIndicator<List<Grocery>>() {
                };
                groceries.clear();
                groceries.addAll(dataSnapshot.getValue(t));
                listener.onGroceriesReceived();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listener.onGroceriesReceived();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int key = Integer.parseInt(dataSnapshot.getKey());
                groceries.remove(key);
                listener.onGroceriesReceived();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
                    listener.onMessage(databaseError.getMessage());
                } else {
                    listener.onMessage("Date saved successfully.");
                }
            }
        });
    }
}


