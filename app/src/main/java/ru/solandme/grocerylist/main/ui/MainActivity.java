package ru.solandme.grocerylist.main.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.main.adapters.GroceryAdapter;
import ru.solandme.grocerylist.model.Grocery;

public class MainActivity extends AppCompatActivity {
    private RecyclerView groceryRV;
    private List<Grocery> groceries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceryRV = (RecyclerView) findViewById(R.id.grocery_rv);
        groceryRV.setLayoutManager(new LinearLayoutManager(this));

        GroceryAdapter groceryAdapter = new GroceryAdapter(groceries);
        groceryRV.setAdapter(groceryAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = database.getReference("grocer");


    }
}
