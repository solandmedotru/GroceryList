package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.main.presenters.IMainPresenter;
import ru.solandme.grocerylist.main.presenters.MainPresenter;
import ru.solandme.grocerylist.model.Grocery;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("grocers");
    FirebaseRecyclerAdapter<Grocery, GroceryViewHolder> groceryAdapter;

    private EditText groceryAddText;

    private IMainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);

        groceryAddText = (EditText) findViewById(R.id.grocery_add_text);
        Button btnAddItem = (Button) findViewById(R.id.btnAdd);
        btnAddItem.setOnClickListener(this);
        RecyclerView groceryRV = (RecyclerView) findViewById(R.id.grocery_rv);
        groceryRV.setLayoutManager(new LinearLayoutManager(this));


        groceryAdapter = new FirebaseRecyclerAdapter<Grocery, GroceryViewHolder>(
                Grocery.class,
                R.layout.grocery_row,
                GroceryViewHolder.class,
                rootRef
        ) {
            @Override
            protected void populateViewHolder(final GroceryViewHolder viewHolder, Grocery model, final int position) {
                viewHolder.setGroceryName(model.getName());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            int position = viewHolder.getAdapterPosition();
                            groceryAdapter.getRef(position).removeValue();
                        } catch (NullPointerException e) {
                            Toast.makeText(getApplicationContext(), "No Items", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        groceryRV.setAdapter(groceryAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAdd:
                String item = groceryAddText.getText().toString().trim();
                Grocery grocery = new Grocery(item);
                mainPresenter.onAddItem(grocery);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        groceryAdapter.cleanup();
    }
}
