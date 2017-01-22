package ru.solandme.grocerylist.main.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.solandme.grocerylist.R;
import ru.solandme.grocerylist.model.ShoppingList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("shoppingLists");
    FirebaseListAdapter<ShoppingList> firebaseListAdapter;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        if (savedInstanceState == null) {
            replaceFragment("home");
        }
    }

    private void initUI() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        firebaseListAdapter = new FirebaseListAdapter<ShoppingList>(this, ShoppingList.class, android.R.layout.two_line_list_item, rootRef) {
            @Override
            protected void populateView(View view, ShoppingList shoppingList, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(shoppingList.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(shoppingList.getOwner());
            }
        };

        mDrawerList.setAdapter(firebaseListAdapter);
        mDrawerList.setOnItemClickListener(this);
    }

    private void replaceFragment(String key) {
        Fragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("key", key);
        fragment.setArguments(args);
        Log.d(TAG, "onItemClick: " + key);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, key)
                .commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String key = firebaseListAdapter.getRef(position).getKey();

        replaceFragment(key);

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseListAdapter.cleanup();
    }
}


