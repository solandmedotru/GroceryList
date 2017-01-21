package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.main.presenters.IMainPresenter;
import ru.solandme.grocerylist.main.presenters.MainPresenter;
import ru.solandme.grocerylist.main.views.adapters.GroceryAdapter;
import ru.solandme.grocerylist.model.Grocery;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener {
    private GroceryAdapter groceryAdapter;
    private EditText groceryAddText;

    private List<Grocery> groceries = new ArrayList<>();
    private IMainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this, groceries);

        groceryAddText = (EditText) findViewById(R.id.grocery_add_text);
        Button btnAddItem = (Button) findViewById(R.id.btnAdd);
        btnAddItem.setOnClickListener(this);
        RecyclerView groceryRV = (RecyclerView) findViewById(R.id.grocery_rv);
        groceryRV.setLayoutManager(new LinearLayoutManager(this));


        groceryAdapter = new GroceryAdapter(groceries);
        groceryRV.setAdapter(groceryAdapter);

        mainPresenter.refreshGroceries();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateGroceries() {
        groceryAdapter.notifyDataSetChanged();
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
}
