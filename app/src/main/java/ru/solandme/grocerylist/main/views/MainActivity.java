package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.main.presenters.IMainPresenter;
import ru.solandme.grocerylist.main.presenters.MainPresenter;
import ru.solandme.grocerylist.main.views.adapters.GroceryAdapter;
import ru.solandme.grocerylist.model.Grocery;

public class MainActivity extends AppCompatActivity implements IMainView {
    private RecyclerView groceryRV;
    private List<Grocery> groceries = new ArrayList<>();
    private IMainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);

        groceryRV = (RecyclerView) findViewById(R.id.grocery_rv);
        groceryRV.setLayoutManager(new LinearLayoutManager(this));

        GroceryAdapter groceryAdapter = new GroceryAdapter(groceries);
        groceryRV.setAdapter(groceryAdapter);


    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
