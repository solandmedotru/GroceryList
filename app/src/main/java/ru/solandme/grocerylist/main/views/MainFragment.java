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

    public static final String SHOPPING_LISTS = "shoppingLists";
    public static final String GROCERIES_LIST = "groceriesList";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseRecyclerAdapter<Grocery, GroceryViewHolder> groceryAdapter;
    private DatabaseReference keyRef = database.getReference(SHOPPING_LISTS);
    private DatabaseReference dataRef = database.getReference(GROCERIES_LIST);

    private IMainPresenter mainPresenter;

    private EditText groceryAddText;

    private String listId;

    public MainFragment() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        listId = getArguments().getString("listId");
        if (listId != null) {
            dataRef = database.getReference(GROCERIES_LIST).child(listId);
        }

        groceryAddText = (EditText) view.findViewById(R.id.grocery_add_text);
        Button btnAddItem = (Button) view.findViewById(R.id.btnAdd);
        btnAddItem.setOnClickListener(this);
        RecyclerView groceryRV = (RecyclerView) view.findViewById(R.id.grocery_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        groceryRV.setLayoutManager(layoutManager);

        groceryAdapter = new FirebaseRecyclerAdapter<Grocery, GroceryViewHolder>(
                Grocery.class,
                R.layout.grocery_row,
                GroceryViewHolder.class,
                dataRef
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
                Grocery grocery = new Grocery(item, listId);
                mainPresenter.addItem(grocery, grocery.getListID());
                break;
        }
        groceryAddText.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        groceryAdapter.cleanup();
    }

}
