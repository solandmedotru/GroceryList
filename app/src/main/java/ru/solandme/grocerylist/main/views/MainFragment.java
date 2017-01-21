package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MainFragment extends Fragment implements IMainView, View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseRecyclerAdapter<Grocery, GroceryViewHolder> groceryAdapter;
    DatabaseReference rootRef;


    private EditText groceryAddText;
    private IMainPresenter mainPresenter;

    public MainFragment() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        String key = getArguments().getString("key");
        if (key != null) {
            rootRef = database.getReference(key);
        }

        groceryAddText = (EditText) getView().findViewById(R.id.grocery_add_text);
        Button btnAddItem = (Button) getView().findViewById(R.id.btnAdd);
        btnAddItem.setOnClickListener(this);
        RecyclerView groceryRV = (RecyclerView) getView().findViewById(R.id.grocery_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        groceryRV.setLayoutManager(layoutManager);

        groceryAdapter = new FirebaseRecyclerAdapter<Grocery, GroceryViewHolder>(
                Grocery.class,
                R.layout.grocery_row,
                GroceryViewHolder.class,
                rootRef
        ) {
            @Override
            protected void populateViewHolder(final GroceryViewHolder viewHolder, Grocery model, int position) {
                viewHolder.setGroceryName(model.getName());

                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int position = viewHolder.getAdapterPosition();
                        groceryAdapter.getRef(position).removeValue();
                        return true;
                    }
                });
            }
        };
        groceryRV.setAdapter(groceryAdapter);
        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        groceryAdapter.cleanup();
    }

}
